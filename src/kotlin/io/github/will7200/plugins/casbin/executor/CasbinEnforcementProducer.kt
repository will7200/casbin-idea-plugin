package io.github.will7200.plugins.casbin.executor

import com.googlecode.aviator.AviatorEvaluator
import com.googlecode.aviator.runtime.function.LambdaFunction
import com.googlecode.aviator.utils.Env
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiManager
import io.github.will7200.plugins.casbin.CasbinError
import io.github.will7200.plugins.casbin.CasbinExecutorProducer
import io.github.will7200.plugins.casbin.CasbinExecutorRequest
import io.github.will7200.plugins.casbin.CasbinTopics
import io.github.will7200.plugins.casbin.ext.AviatorLambdaCasbinAdapter
import io.github.will7200.plugins.casbin.language.CasbinLanguage
import io.github.will7200.plugins.casbin.language.psi.CasbinPsiFile
import org.casbin.jcasbin.main.Enforcer
import java.io.File


class CasbinEnforcementProducer(
    private val modelPath: String?,
    private val policyFile: String?,
    private val project: Project
) : CasbinExecutorProducer {
    private var log: Logger = Logger.getInstance(CasbinEnforcementProducer::class.java)
    private var enforcer: Enforcer
    private val bus = project.messageBus

    // expectedValAmount hold the amount of rvals that the enforcer is expecting
    // if the the model definition is not registered as a casbin file it will create an in memory psiFile
    private val expectedValAmount: Int? by lazy {
        if (modelPath != null) {
            LocalFileSystem.getInstance().findFileByIoFile(File(modelPath))?.let {
                val psiFile = PsiManager.getInstance(project).findFile(it) ?: return@lazy null
                if (psiFile is CasbinPsiFile) {
                    val propertyDefinition = psiFile.sectionByName("request_definition")?.getProperty("r")
                    return@lazy propertyDefinition?.optionValues?.valueTuple?.attributeDefinitionList?.size
                } else {
                    // using a non registered casbin extension we have to create our own psiFile
                    val newPsiFile = PsiFileFactory.getInstance(project).createFileFromText(
                        modelPath,
                        CasbinLanguage,
                        File(modelPath).readText(),
                        false,
                        true,
                        false,
                        it
                    ) as CasbinPsiFile
                    val propertyDefinition = newPsiFile.sectionByName("request_definition")?.getProperty("r")
                    return@lazy propertyDefinition?.optionValues?.valueTuple?.attributeDefinitionList?.size
                }
            }
        }
        return@lazy null
    }

    init {
        try {
            enforcer = Enforcer(modelPath, policyFile)
        } catch (exception: Exception) {
            throw CasbinError("Couldn't create an enforcer", exception)
        }
        bus.connect(this).subscribe(CasbinTopics.EXECUTOR_REQUEST_TOPIC, this)
    }

    private fun executeRequest(casbinRequest: CasbinExecutorRequest) {
        when (casbinRequest) {
            is CasbinExecutorRequest.CasbinUpdateCustomFunctions -> {
                val expression = AviatorEvaluator.compile(casbinRequest.aviatorScript)
                val export = Env()
                expression.execute(export)
                export.entries.forEach {
                    when (it.value) {
                        is LambdaFunction -> {
                            enforcer.addFunction(it.key, AviatorLambdaCasbinAdapter(it.value as LambdaFunction))
                        }
                    }
                }
            }
            is CasbinExecutorRequest.CasbinEnforcementRequest -> {
                if (casbinRequest.modelFile.replace(
                        "\\",
                        "/"
                    ) == this.modelPath && casbinRequest.policyFile.replace("\\", "/") == this.policyFile
                ) {
                    executeEnforcement(casbinRequest)
                }
            }
            else -> {
                log.warn("skipping. unknown type handle ${casbinRequest.toString()}")
            }
        }
    }

    private fun executeEnforcement(casbinRequest: CasbinExecutorRequest.CasbinEnforcementRequest) {
        val publisher = bus.syncPublisher(CasbinTopics.EXECUTOR_RESPONSE_TOPIC)

        publisher.beforeProcessing(casbinRequest);
        try {
            when {
                casbinRequest.rvals.isEmpty() -> {
                    casbinRequest.processed = false
                    casbinRequest.result = CasbinExecutorRequest.Decision.ERROR
                    casbinRequest.message = "Empty rvals"
                }
                casbinRequest.rvals.size == 1 && casbinRequest.rvals[0].isEmpty() -> {
                    casbinRequest.processed = false
                    casbinRequest.result = CasbinExecutorRequest.Decision.INVALID
                    casbinRequest.message = "Skipped empty line"
                }
                casbinRequest.rvals.size != expectedValAmount -> {
                    casbinRequest.processed = false
                    casbinRequest.result = CasbinExecutorRequest.Decision.ERROR
                    casbinRequest.message = "Expecting $expectedValAmount values instead of ${casbinRequest.rvals.size}"
                }
                else -> {
                    if (enforcer.enforce(*casbinRequest.rvals)) {
                        casbinRequest.result = CasbinExecutorRequest.Decision.ALLOW
                    } else {
                        casbinRequest.result = CasbinExecutorRequest.Decision.DENY
                    }
                    casbinRequest.processed = true
                }
            }
            publisher.afterProcessing(casbinRequest)
        } catch (ex: Exception) {
            casbinRequest.result = CasbinExecutorRequest.Decision.ERROR
            casbinRequest.message = ex.message
        }
    }

    override fun processRequest(request: CasbinExecutorRequest) {
        executeRequest(request)
    }

    override fun dispose() {
    }
}
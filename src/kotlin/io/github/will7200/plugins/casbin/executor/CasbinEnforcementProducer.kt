package io.github.will7200.plugins.casbin.executor

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.psi.PsiManager
import io.github.will7200.plugins.casbin.CasbinError
import io.github.will7200.plugins.casbin.CasbinExecutorProducer
import io.github.will7200.plugins.casbin.CasbinExecutorRequest
import io.github.will7200.plugins.casbin.CasbinTopics
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
    // TODO: Does having a file with a non registered casbin extension give the correct PSIFile?
    private val expectedValAmount: Int? by lazy {
        if (modelPath != null) {
            LocalFileSystem.getInstance().findFileByIoFile(File(modelPath))?.let {
                val psiFile = PsiManager.getInstance(project).findFile(it)
                if (psiFile is CasbinPsiFile) {
                    val propertyDefinition = psiFile.sectionByName("request_definition")?.getProperty("r")
                    return@lazy propertyDefinition?.optionValues?.valueTuple?.attributeDefinitionList?.size
                } else {
                    return@lazy null
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
        bus.connect().subscribe(CasbinTopics.EXECUTOR_REQUEST_TOPIC, this)
    }

    private fun executeRequest(casbinRequest: CasbinExecutorRequest) {
        when (casbinRequest) {
            is CasbinExecutorRequest.CasbinEnforcementRequest -> {
                if (casbinRequest.modelFile == this.modelPath && casbinRequest.policyFile == this.policyFile) {
                    executeEnforcement(casbinRequest)
                }
            }
            else -> {
                log.warn("unknown type handle ${casbinRequest.toString()}")
                log.warn("skipping")
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
            log.warn(ex.toString())
            ex.printStackTrace()
        }
    }

    override fun processRequest(request: CasbinExecutorRequest) {
        executeRequest(request)
    }
}
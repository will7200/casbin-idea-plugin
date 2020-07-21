package io.github.will7200.plugins.casbin.executor

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.diagnostic.Logger
import com.intellij.util.messages.MessageBus
import io.github.will7200.plugins.casbin.CasbinExecutorProducer
import io.github.will7200.plugins.casbin.CasbinExecutorRequest
import io.github.will7200.plugins.casbin.CasbinTopics
import io.github.will7200.plugins.casbin.view.ui.CasbinRequestGutterIconRenderer
import org.casbin.jcasbin.main.Enforcer


class CasbinEnforcementProducer(
    private val modelPath: String?,
    private val policyFile: String?,
    private val bus: MessageBus
) : CasbinExecutorProducer {
    private var log: Logger = Logger.getInstance(CasbinEnforcementProducer::class.java)
    private var enforcer: Enforcer = Enforcer(modelPath, policyFile)
    private var lineNum: Int = 0

    init {
        bus.connect().subscribe(CasbinTopics.REQUEST_TOPIC, this)
    }

    private fun executeRequest(casbinRequest: CasbinExecutorRequest) {
        when (casbinRequest) {
            is CasbinExecutorRequest.CasbinEnforcementRequest -> {
                if (casbinRequest.modelFile == this.modelPath && casbinRequest.policyFile == this.policyFile) {
                    executeEnforcement(casbinRequest)
                }
            }
            is CasbinExecutorRequest.CasbinFileChangeRequest -> {
                when (casbinRequest) {
                    is CasbinExecutorRequest.CasbinModelFileChangeRequest -> {
                        // TODO: Model File Change
                    }
                    is CasbinExecutorRequest.CasbinPolicyFileChangeRequest -> {
                        // TODO: Policy File Change
                    }
                }
            }
            else -> {
                log.warn("unknown type handle ${casbinRequest.toString()}")
                log.warn("skipping")
            }
        }
    }

    private fun executeEnforcement(casbinRequest: CasbinExecutorRequest.CasbinEnforcementRequest) {
        val publisher = bus.syncPublisher(CasbinTopics.RESPONSE_TOPIC)

        publisher.beforeProcessing(casbinRequest);
        try {
            if (enforcer.enforce(*casbinRequest.rvals)) {
                casbinRequest.result = CasbinExecutorRequest.Decision.ALLOW
            } else {
                casbinRequest.result = CasbinExecutorRequest.Decision.DENY
            }
            log.warn(casbinRequest.result.toString())
            casbinRequest.processed = true
            publisher.afterProcessing(casbinRequest)
        } catch (ex: Exception) {
            casbinRequest.result = CasbinExecutorRequest.Decision.ERROR
            log.warn(ex.toString())
            ex.printStackTrace()
        }
        ApplicationManager.getApplication().invokeLater {
            ApplicationManager.getApplication().runWriteAction() {
                val highlighter =
                    casbinRequest.model!!.addLineHighlighter(
                        lineNum++,
                        0,
                        null
                    )
                highlighter.gutterIconRenderer =
                    CasbinRequestGutterIconRenderer(casbinRequest)
            }
        }
    }

    override fun processRequest(request: CasbinExecutorRequest) {
        log.warn(request.toString())
        executeRequest(request)
    }
}
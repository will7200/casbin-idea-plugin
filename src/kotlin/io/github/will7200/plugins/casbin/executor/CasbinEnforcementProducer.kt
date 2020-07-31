package io.github.will7200.plugins.casbin.executor

import com.intellij.openapi.diagnostic.Logger
import com.intellij.util.messages.MessageBus
import io.github.will7200.plugins.casbin.CasbinError
import io.github.will7200.plugins.casbin.CasbinExecutorProducer
import io.github.will7200.plugins.casbin.CasbinExecutorRequest
import io.github.will7200.plugins.casbin.CasbinTopics
import org.casbin.jcasbin.main.Enforcer


class CasbinEnforcementProducer(
    private val modelPath: String?,
    private val policyFile: String?,
    private val bus: MessageBus
) : CasbinExecutorProducer {
    private var log: Logger = Logger.getInstance(CasbinEnforcementProducer::class.java)
    private var enforcer: Enforcer

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
        val publisher = bus.syncPublisher(CasbinTopics.EXECUTOR_RESPONSE_TOPIC)

        publisher.beforeProcessing(casbinRequest);
        try {
            when {
                casbinRequest.rvals.isEmpty() || (casbinRequest.rvals.size == 1 && casbinRequest.rvals[0].isEmpty()) -> {
                    casbinRequest.processed = false
                    casbinRequest.result = CasbinExecutorRequest.Decision.ERROR
                    casbinRequest.message = "Empty rvals"
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
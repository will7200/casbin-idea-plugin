package io.github.will7200.plugins.casbin.executor

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.jetbrains.rd.util.ConcurrentHashMap
import io.github.will7200.plugins.casbin.CasbinError
import io.github.will7200.plugins.casbin.CasbinExecutorRequest
import io.github.will7200.plugins.casbin.CasbinExecutorService
import io.github.will7200.plugins.casbin.CasbinTopics
import io.github.will7200.plugins.casbin.view.ui.CasbinExecutorErrorsNotifier
import java.time.LocalDateTime

typealias EnforcerKey = Pair<String, String>

class CasbinExecutorManager(private val myProject: Project) : CasbinExecutorService() {
    private val log: Logger = Logger.getInstance(CasbinExecutorManager::class.java)
    private val enforcers: MutableUsageMap<EnforcerKey, CasbinEnforcementProducer> = MutableUsageMap()
    private val maxEnforcers: Int = 10

    init {
        myProject.messageBus.connect().subscribe(CasbinTopics.EXECUTOR_REQUEST_TOPIC, this)
    }

    override fun createEnforcer(request: CasbinExecutorRequest.CasbinCreateEnforcer) {
        val lk = Pair(request.modelFile.replace("\\", "/"), request.policyFile.replace("\\", "/"))
        if (enforcers.size > maxEnforcers) {
            val removeKeys = enforcers.leastUsedKeys(enforcers.size - maxEnforcers)
            for (key in removeKeys) {
                enforcers.remove(key)
            }
        }
        val found = enforcers[lk]
        if (found != null) {
            return
        }
        try {
            val producer = CasbinEnforcementProducer(request.modelFile, request.policyFile, myProject)
            enforcers[lk] = producer
        } catch (ce: CasbinError) {
            CasbinExecutorErrorsNotifier.notify(myProject, ce.message, ce.details)
        }
        return
    }

    override fun executeEnforcement(request: CasbinExecutorRequest.CasbinEnforcementRequest) {
        val lk = Pair(request.modelFile.replace("\\", "/"), request.policyFile.replace("\\", "/"))
        if (enforcers[lk] == null) {
            return
        }
        myProject.messageBus.syncPublisher(CasbinTopics.EXECUTOR_REQUEST_TOPIC).processRequest(request)
    }

    override fun processRequest(request: CasbinExecutorRequest) {
        val publisher = myProject.messageBus.syncPublisher(CasbinTopics.EXECUTOR_RESPONSE_TOPIC)
        publisher.beforeProcessing(request)
        when (request) {
            is CasbinExecutorRequest.CasbinFileChangeNotify -> {
                for (key in enforcers.keys()) {
                    if (key.first == request.filePath || key.second == request.filePath) {
                        reInitializeEnforce(key)
                        request.enforcerSwapped = true
                        break
                    }
                }
            }
        }
        publisher.afterProcessing(request)
    }

    private fun reInitializeEnforce(key: EnforcerKey) {
        val newEnforcer = CasbinEnforcementProducer(key.first, key.second, myProject)
        enforcers.remove(key)
        enforcers[key] = newEnforcer
    }

    companion object {
        class MutableUsageMap<K, V> : ConcurrentHashMap<K, V>() {
            private val keyUsageMap: MutableMap<K, LocalDateTime> = mutableMapOf()

            override fun get(key: K): V? {
                keyUsageMap[key] = LocalDateTime.now()
                return super.get(key)
            }

            override fun remove(key: K): V? {
                keyUsageMap.remove(key)
                return super.remove(key)
            }

            fun leastUsedKeys(n: Int?): List<K> {
                val r = keyUsageMap.entries.sortedBy { it.value }.map { it.key }
                if (n != null) {
                    return r.take(n)
                }
                return r
            }
        }
    }
}
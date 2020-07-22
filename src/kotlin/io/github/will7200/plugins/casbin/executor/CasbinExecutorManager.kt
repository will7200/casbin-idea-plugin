package io.github.will7200.plugins.casbin.executor

import com.intellij.openapi.project.Project
import io.github.will7200.plugins.casbin.CasbinExecutorRequest
import io.github.will7200.plugins.casbin.CasbinExecutorService
import io.github.will7200.plugins.casbin.CasbinTopics
import java.time.LocalDateTime

class CasbinExecutorManager(private val myProject: Project?) : CasbinExecutorService() {
    private val enforcers: MutableUsageMap<Pair<String, String>, CasbinEnforcementProducer> = MutableUsageMap()
    private val maxEnforcers: Int = 10

    override fun executeEnforcement(request: CasbinExecutorRequest.CasbinEnforcementRequest) {
        val lk = Pair(request.modelFile, request.policyFile)
        if (enforcers[lk] == null) {
            if (enforcers.size > maxEnforcers) {
                val removeKeys = enforcers.leastUsedKeys(enforcers.size - maxEnforcers)
                for (key in removeKeys) {
                    enforcers.remove(key)
                }
            }
            enforcers[lk] = CasbinEnforcementProducer(request.modelFile, request.policyFile, myProject!!.messageBus)
        }
        processRequest(request)
    }

    override fun processRequest(request: CasbinExecutorRequest) {
        myProject?.messageBus?.syncPublisher(CasbinTopics.EXECUTOR_REQUEST_TOPIC)?.processRequest(request)
    }

    companion object {
        class MutableUsageMap<K, V> : LinkedHashMap<K, V>() {
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
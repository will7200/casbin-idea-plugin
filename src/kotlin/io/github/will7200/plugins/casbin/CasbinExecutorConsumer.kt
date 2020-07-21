package io.github.will7200.plugins.casbin

interface CasbinExecutorConsumer {
    fun beforeProcessing(request: CasbinExecutorRequest)
    fun afterProcessing(request: CasbinExecutorRequest)
}
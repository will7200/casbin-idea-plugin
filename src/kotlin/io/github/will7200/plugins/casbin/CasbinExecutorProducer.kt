package io.github.will7200.plugins.casbin

interface CasbinExecutorProducer {
    fun processRequest(request: CasbinExecutorRequest)
}
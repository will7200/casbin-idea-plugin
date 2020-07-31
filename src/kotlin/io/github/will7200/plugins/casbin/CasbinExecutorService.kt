package io.github.will7200.plugins.casbin


abstract class CasbinExecutorService : CasbinExecutorProducer {
    abstract fun executeEnforcement(request: CasbinExecutorRequest.CasbinEnforcementRequest)
    abstract fun createEnforcer(request: CasbinExecutorRequest.CasbinCreateEnforcer)
}
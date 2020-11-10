package io.github.will7200.plugins.casbin

import com.intellij.openapi.Disposable

interface CasbinExecutorProducer : Disposable {
    fun processRequest(request: CasbinExecutorRequest)
}
package io.github.will7200.plugins.casbin

import kotlinx.coroutines.ExperimentalCoroutinesApi

interface CasbinExecutorConsumer {
    fun beforeProcessing(request: CasbinExecutorRequest)

    @ExperimentalCoroutinesApi
    fun afterProcessing(request: CasbinExecutorRequest)
}
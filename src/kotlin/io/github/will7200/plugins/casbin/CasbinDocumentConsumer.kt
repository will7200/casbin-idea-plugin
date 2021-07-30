package io.github.will7200.plugins.casbin

import kotlinx.coroutines.ExperimentalCoroutinesApi

interface CasbinDocumentConsumer {
    fun beforeProcessing(request: CasbinDocumentRequest)

    @ExperimentalCoroutinesApi
    fun afterProcessing(request: CasbinDocumentRequest)
}
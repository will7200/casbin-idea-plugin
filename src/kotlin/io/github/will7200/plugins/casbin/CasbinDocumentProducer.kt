package io.github.will7200.plugins.casbin

import kotlinx.coroutines.ExperimentalCoroutinesApi

interface CasbinDocumentProducer {
    @ExperimentalCoroutinesApi
    fun processChange(change: CasbinDocumentRequest)
}
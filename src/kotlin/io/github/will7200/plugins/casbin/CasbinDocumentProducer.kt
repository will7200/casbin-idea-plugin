package io.github.will7200.plugins.casbin

interface CasbinDocumentProducer {
    fun processChange(change: CasbinDocumentRequest)
}
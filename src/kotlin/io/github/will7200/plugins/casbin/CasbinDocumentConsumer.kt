package io.github.will7200.plugins.casbin

interface CasbinDocumentConsumer {
    fun beforeProcessing(request: CasbinDocumentRequest)
    fun afterProcessing(request: CasbinDocumentRequest)
}
package io.github.will7200.plugins.casbin

import com.intellij.openapi.editor.Document


abstract class CasbinDocumentService : CasbinDocumentProducer {
    abstract fun connectDocument(document: Document?)
}
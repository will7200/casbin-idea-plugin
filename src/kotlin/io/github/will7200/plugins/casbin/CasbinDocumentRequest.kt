package io.github.will7200.plugins.casbin

import com.intellij.openapi.editor.Document


open class CasbinDocumentRequest {
    class ContentRemove(private val document: Document) : CasbinDocumentRequest() {

    }

    class ContentAdded(private val document: Document) : CasbinDocumentRequest() {

    }
}

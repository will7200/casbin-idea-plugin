package io.github.will7200.plugins.casbin

import com.github.difflib.patch.Patch
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.markup.MarkupModel
import io.github.will7200.plugins.casbin.view.editors.CasbinCSVEditor
import io.github.will7200.plugins.casbin.view.ui.CasbinExecutorToolWindow


open class CasbinDocumentRequest(
    open val document: Document
) {
    class ExecuteEntireDocument(
        val editor: CasbinCSVEditor,
        document: Document,
        val toolWindow: CasbinExecutorToolWindow
    ) : CasbinDocumentRequest(document) {

    }

    class ContentRemoved(
        val editor: CasbinCSVEditor,
        override val document: Document,
        toolWindow: CasbinExecutorToolWindow
    ) : CasbinDocumentRequest(document) {
        var patch: Patch<String>? = null
    }

    class ContentAdded(
        val editor: CasbinCSVEditor,
        override val document: Document,
        val toolWindow: CasbinExecutorToolWindow
    ) :
        CasbinDocumentRequest(document) {

    }

    class RemoveHighlighter(private val startLine: Int, private val endLine: Int, document: Document) :
        CasbinDocumentRequest(document) {
        var model: MarkupModel? = null

        constructor(afterLine: Int, document: Document) : this(afterLine, Int.MAX_VALUE, document)

        fun range(): Pair<Int, Int> {
            return Pair(startLine, endLine)
        }
    }
}

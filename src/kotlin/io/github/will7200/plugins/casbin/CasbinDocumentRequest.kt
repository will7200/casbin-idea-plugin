package io.github.will7200.plugins.casbin

import com.github.difflib.patch.Patch
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.markup.MarkupModel
import io.github.will7200.plugins.casbin.view.editors.CasbinCSVEditor
import io.github.will7200.plugins.casbin.view.ui.CasbinExecutorToolWindow


open class CasbinDocumentRequest(
    open val document: Document,
    open val toolWindow: CasbinExecutorToolWindow
) {
    class ExecuteEntireDocument(
        val editor: CasbinCSVEditor,
        document: Document,
        toolWindow: CasbinExecutorToolWindow
    ) : CasbinDocumentRequest(document, toolWindow) {

    }

    class ContentRemoved(
        val editor: CasbinCSVEditor,
        document: Document,
        toolWindow: CasbinExecutorToolWindow
    ) : CasbinDocumentRequest(document, toolWindow) {
        var patch: Patch<String>? = null
    }

    class ContentAdded(
        val editor: CasbinCSVEditor,
        override val document: Document,
        toolWindow: CasbinExecutorToolWindow
    ) :
        CasbinDocumentRequest(document, toolWindow) {

    }

    class RemoveHighlighter(
        private val startLine: Int,
        private val endLine: Int,
        document: Document,
        toolWindow: CasbinExecutorToolWindow
    ) :
        CasbinDocumentRequest(document, toolWindow) {
        var model: MarkupModel? = null

        constructor(afterLine: Int, document: Document, toolWindow: CasbinExecutorToolWindow) : this(
            afterLine,
            Int.MAX_VALUE,
            document,
            toolWindow
        )

        fun range(): Pair<Int, Int> {
            return Pair(startLine, endLine)
        }
    }
}

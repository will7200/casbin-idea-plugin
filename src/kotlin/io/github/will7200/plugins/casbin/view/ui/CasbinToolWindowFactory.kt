package io.github.will7200.plugins.casbin.view.ui

import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import io.github.will7200.plugins.casbin.CasbinDocumentRequest
import io.github.will7200.plugins.casbin.CasbinDocumentService
import io.github.will7200.plugins.casbin.view.editors.CasbinCSVEditor


class CasbinToolWindowFactory : ToolWindowFactory {
    private val log: Logger = Logger.getInstance(CasbinToolWindowFactory::class.java)
    private lateinit var project: Project
    private lateinit var myToolWindow: CasbinExecutorToolWindow

    // Create the tool window content.
    override fun createToolWindowContent(
        project: Project,
        toolWindow: ToolWindow
    ) {
        this.project = project
        myToolWindow = CasbinExecutorToolWindow(project, toolWindow)
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content: Content = contentFactory.createContent(myToolWindow.content, "Main", false)
        toolWindow.contentManager.addContent(content)
        setupListeners()
    }

    private fun setupListeners() {
        val casbinDocumentManager = project.service<CasbinDocumentService>()
        myToolWindow.requestEditorPane.run {
            casbinDocumentManager.connectDocument(document)
            document.addDocumentListener(object : DocumentListener {
                override fun documentChanged(event: DocumentEvent) {
                    super.documentChanged(event)
                    if (event.newFragment.toString().contains("\n")) {
                        casbinDocumentManager.processChange(
                            CasbinDocumentRequest.ContentAdded(
                                myToolWindow.requestEditorPane as CasbinCSVEditor,
                                document,
                                myToolWindow
                            )
                        )
                    } else if (event.newLength < event.oldLength) {
                        log.warn("context changed")
                        casbinDocumentManager.processChange(
                            CasbinDocumentRequest.ContentRemoved(
                                myToolWindow.requestEditorPane as CasbinCSVEditor,
                                document,
                                myToolWindow
                            )
                        )
                    }
                }
            })
        }
        // TODO: Add Listeners for Run Test Buttons and Async Check Box
    }
}
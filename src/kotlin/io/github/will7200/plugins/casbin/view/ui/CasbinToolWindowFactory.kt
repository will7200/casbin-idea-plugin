package io.github.will7200.plugins.casbin.view.ui

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import io.github.will7200.plugins.casbin.CasbinDocumentRequest
import io.github.will7200.plugins.casbin.CasbinDocumentService
import io.github.will7200.plugins.casbin.CasbinTopics
import io.github.will7200.plugins.casbin.view.editors.CasbinCSVEditor
import java.awt.event.ItemEvent


class CasbinToolWindowFactory : ToolWindowFactory {
    private lateinit var casbinDocumentManager: CasbinDocumentService
    private val log: Logger = Logger.getInstance(CasbinToolWindowFactory::class.java)
    private lateinit var project: Project
    private lateinit var myToolWindow: CasbinExecutorToolWindow
    private var dispose: Disposable? = null

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
        casbinDocumentManager = project.service()
        setupListeners()
    }

    private fun setupListeners() {
        myToolWindow.requestEditorPane.run {
            casbinDocumentManager.connectDocument(document)
        }

        if (myToolWindow.runAsyncCheckBox.isSelected) {
            connectDocumentAsync()
        }
        myToolWindow.runTestButton.addActionListener {
            project.messageBus.syncPublisher(CasbinTopics.DOCUMENT_REQUEST_TOPIC)
                .processChange(
                    CasbinDocumentRequest.ExecuteEntireDocument(
                        myToolWindow.requestEditorPane as CasbinCSVEditor,
                        myToolWindow.requestEditorPane.document,
                        myToolWindow
                    )
                )
        }

        myToolWindow.runAsyncCheckBox.addItemListener { event ->
            if (event.stateChange == ItemEvent.SELECTED) {
                connectDocumentAsync()
            } else {
                disconnectDocument()
            }
        }
    }

    private fun connectDocumentAsync() {
        dispose = Disposable {}
        myToolWindow.requestEditorPane.run {
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
            }, dispose!!)
        }
    }

    private fun disconnectDocument() {
        myToolWindow.requestEditorPane.run {
            dispose?.let {
                Disposer.dispose(it)
            }
        }
    }
}
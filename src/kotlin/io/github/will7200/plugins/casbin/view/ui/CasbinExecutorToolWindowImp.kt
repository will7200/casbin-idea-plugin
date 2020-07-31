package io.github.will7200.plugins.casbin.view.ui

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.ToolWindow
import io.github.will7200.plugins.casbin.CasbinDocumentRequest
import io.github.will7200.plugins.casbin.CasbinDocumentService
import io.github.will7200.plugins.casbin.CasbinTopics
import io.github.will7200.plugins.casbin.view.editors.CasbinCSVEditor
import java.awt.event.ItemEvent

class CasbinExecutorToolWindowImp(private val project: Project, private val toolWindow: ToolWindow) :
    CasbinExecutorToolWindow(project, toolWindow) {
    private val log: Logger = Logger.getInstance(CasbinExecutorToolWindowImp::class.java)
    private var casbinDocumentManager: CasbinDocumentService = project.service()
    private var dispose: Disposable? = null

    init {
        setupListeners()
    }

    private fun setupListeners() {
        requestEditorPane.run {
            casbinDocumentManager.connectDocument(document)
        }

        if (runAsyncCheckBox.isSelected) {
            connectDocumentAsync()
        }
        runTestButton.addActionListener {
            project.messageBus.syncPublisher(CasbinTopics.DOCUMENT_REQUEST_TOPIC)
                .processChange(
                    CasbinDocumentRequest.ExecuteEntireDocument(
                        requestEditorPane as CasbinCSVEditor,
                        requestEditorPane.document,
                        this
                    )
                )
        }

        runAsyncCheckBox.addItemListener { event ->
            if (event.stateChange == ItemEvent.SELECTED) {
                connectDocumentAsync()
            } else {
                disconnectDocument()
            }
        }
    }

    private fun connectDocumentAsync() {
        dispose = Disposable {}
        requestEditorPane.run {
            document.addDocumentListener(object : DocumentListener {
                override fun documentChanged(event: DocumentEvent) {
                    super.documentChanged(event)
                    if (event.newFragment.toString().contains("\n")) {
                        casbinDocumentManager.processChange(
                            CasbinDocumentRequest.ContentAdded(
                                requestEditorPane as CasbinCSVEditor,
                                document,
                                this@CasbinExecutorToolWindowImp
                            )
                        )
                    } else if (event.newLength < event.oldLength) {
                        log.warn("context changed")
                        casbinDocumentManager.processChange(
                            CasbinDocumentRequest.ContentRemoved(
                                requestEditorPane as CasbinCSVEditor,
                                document,
                                this@CasbinExecutorToolWindowImp
                            )
                        )
                    }
                }
            }, dispose!!)
        }
    }

    private fun disconnectDocument() {
        requestEditorPane.run {
            dispose?.let {
                Disposer.dispose(it)
            }
        }
    }
}
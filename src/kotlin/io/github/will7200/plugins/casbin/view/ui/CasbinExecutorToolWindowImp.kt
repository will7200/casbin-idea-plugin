package io.github.will7200.plugins.casbin.view.ui

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.ToolWindow
import io.github.will7200.plugins.casbin.*
import io.github.will7200.plugins.casbin.view.editors.CasbinCSVEditor
import java.awt.event.ItemEvent

class CasbinExecutorToolWindowImp(private val project: Project, private val toolWindow: ToolWindow) :
    CasbinExecutorToolWindow(project, toolWindow), CasbinExecutorConsumer {
    private val log: Logger = Logger.getInstance(CasbinExecutorToolWindowImp::class.java)
    private var casbinDocumentManager: CasbinDocumentService = project.service()
    private var dispose: Disposable? = null

    init {
        setupListeners()
        val connection = project.messageBus.connect()
        connection.subscribe(CasbinTopics.EXECUTOR_RESPONSE_TOPIC, this)
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

    override fun beforeProcessing(request: CasbinExecutorRequest) {
        TODO("Not yet implemented")
    }

    override fun afterProcessing(request: CasbinExecutorRequest) {
        when (request) {
            is CasbinExecutorRequest.CasbinFileChangeNotify -> {
                if (request.enforcerSwapped &&
                    (request.filePath == modelDefinitionFile.replace("\\", "/")
                            || request.filePath == policyFile.replace("\\", "/"))
                ) {
                    requestEditorPane.editor?.markupModel?.removeAllHighlighters()
                }
            }
        }
    }
}
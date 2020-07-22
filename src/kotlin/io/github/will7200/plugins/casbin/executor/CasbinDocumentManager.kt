package io.github.will7200.plugins.casbin.executor

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project
import io.github.will7200.plugins.casbin.*

class CasbinDocumentManager(private val project: Project?) : CasbinDocumentService() {
    private val log: Logger = Logger.getInstance(this::class.java)
    var document: Document? = null
    private var cdc: CasbinDocumentConsumer? = null
    private var cdp: CasbinDocumentProducer? = null


    override fun connectDocument(document: Document) {
        this.document = document
        this.cdc = CasbinEnforcementConsumer(project!!)
        this.cdp = CasbinEditorTreeDiff(project)
    }

    override fun processChange(change: CasbinDocumentRequest) {
        when {
            this.document === change.document -> {
                project?.messageBus?.syncPublisher(CasbinTopics.DOCUMENT_REQUEST_TOPIC)?.processChange(change)
            }
            this.document == null -> {
                log.warn("The current document service is not connected to any document")
            }
            else -> {
                log.warn("The current document manager only allows one instance")
            }
        }
    }

}
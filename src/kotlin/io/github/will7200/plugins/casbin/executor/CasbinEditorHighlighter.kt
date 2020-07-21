package io.github.will7200.plugins.casbin.executor

import com.intellij.openapi.project.ProjectManager
import com.intellij.util.messages.MessageBus
import io.github.will7200.plugins.casbin.CasbinDocumentProducer
import io.github.will7200.plugins.casbin.CasbinDocumentRequest
import io.github.will7200.plugins.casbin.CasbinTopics

class CasbinEditorHighlighter : CasbinDocumentProducer {
    private val bus: MessageBus

    constructor() {
        bus = ProjectManager.getInstance().defaultProject.messageBus
        bus.connect().subscribe(CasbinTopics.DOCUMENT_REQUEST_TOPIC, this)
    }

    constructor(pBus: MessageBus) {
        bus = pBus
    }

    override fun processChange(change: CasbinDocumentRequest) {
        TODO("Not yet implemented")
    }

}
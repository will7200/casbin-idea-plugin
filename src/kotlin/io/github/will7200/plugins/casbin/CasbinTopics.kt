package io.github.will7200.plugins.casbin

import com.intellij.util.messages.Topic

interface CasbinTopics {
    companion object {
        val REQUEST_TOPIC = Topic.create(
            "REQUEST_TOPIC",
            CasbinExecutorProducer::class.java,
            Topic.BroadcastDirection.TO_PARENT
        )

        val RESPONSE_TOPIC = Topic.create(
            "RESPONSE_TOPIC",
            CasbinExecutorConsumer::class.java,
            Topic.BroadcastDirection.TO_PARENT
        )

        val DOCUMENT_REQUEST_TOPIC = Topic.create(
            "DOCUMENT_REQUEST_TOPIC",
            CasbinDocumentProducer::class.java,
            Topic.BroadcastDirection.TO_PARENT
        )

        val DOCUMENT_RESPONSE_TOPIC = Topic.create(
            "DOCUMENT_RESPONSE_TOP",
            CasbinDocumentConsumer::class.java,
            Topic.BroadcastDirection.TO_PARENT
        )

    }

}
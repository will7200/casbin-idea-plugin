package io.github.will7200.plugins.casbin.executor

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.vfs.newvfs.BulkFileListener
import com.intellij.openapi.vfs.newvfs.events.VFileEvent
import io.github.will7200.plugins.casbin.CasbinExecutorRequest
import io.github.will7200.plugins.casbin.CasbinTopics
import java.io.File

class CasbinFileListener : BulkFileListener {

    private var project: Project = ProjectManager.getInstance().defaultProject
    private val log: Logger = Logger.getInstance(CasbinFileListener::class.java)
    override fun after(events: MutableList<out VFileEvent>) {
        super.after(events)
        for (event in events) {
            if (File(event.path).extension in arrayOf("csv", "conf")) {
                sendChangeEvent(event.path)
            }
        }
    }

    private fun sendChangeEvent(filePath: String) {
        project.messageBus.syncPublisher(CasbinTopics.EXECUTOR_REQUEST_TOPIC)
            .processRequest(CasbinExecutorRequest.CasbinFileChangeNotify(filePath))
    }

}
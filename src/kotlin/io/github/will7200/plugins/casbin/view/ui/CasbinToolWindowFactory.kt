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
import io.github.will7200.plugins.casbin.CasbinExecutorRequest
import io.github.will7200.plugins.casbin.CasbinExecutorService


class CasbinToolWindowFactory : ToolWindowFactory {
    private val log: Logger = Logger.getInstance(CasbinToolWindowFactory::class.java)
    private var myProject: Project? = null
    private val casbinService: CasbinExecutorService by lazy {
        myProject!!.service<CasbinExecutorService>()
    }

    // Create the tool window content.
    override fun createToolWindowContent(
        project: Project,
        toolWindow: ToolWindow
    ) {
        val myToolWindow =
            CasbinExecutorToolWindow(project, toolWindow)
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content: Content = contentFactory.createContent(myToolWindow.content, "Main", false)
        toolWindow.contentManager.addContent(content)
        myProject = project
        myToolWindow.requestEditorPane.run {
            document.addDocumentListener(object : DocumentListener {
                override fun documentChanged(event: DocumentEvent) {
                    super.documentChanged(event)
                    if (event.newFragment.toString().contains("\n")) {
                        casbinService.executeEnforcement(
                            CasbinExecutorRequest.CasbinEnforcementRequest(
                                myToolWindow.modelDefinitionFile,
                                myToolWindow.policyFile,
                                arrayOf("alice", "data1", "read")
                            ).apply {
                                this.document = myToolWindow.requestEditorPane.document
                                this.model = myToolWindow.requestEditorPane.editor?.markupModel
                            }
                        )
                    } else if (event.newLength < event.oldLength) {
                        log.warn("context changed")
                        // log.warn(PsiDocumentManager.getInstance(project).getPsiFile(this@run.document).toString())
                    }
                }
            })
        }
    }
}
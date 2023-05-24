package io.github.will7200.plugins.casbin.view.ui

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content


class CasbinToolWindowFactory : ToolWindowFactory, DumbAware {

    // Create the tool window content.
    override fun createToolWindowContent(
        project: Project,
        toolWindow: ToolWindow
    ) {
        val myToolWindow = CasbinExecutorToolWindowImp(project, toolWindow)
        val contentFactory = toolWindow.contentManager.factory
        val content: Content = contentFactory.createContent(myToolWindow.content, "Main", false)
        toolWindow.contentManager.addContent(content)
    }
}
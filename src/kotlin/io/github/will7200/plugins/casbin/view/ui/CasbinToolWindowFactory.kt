package io.github.will7200.plugins.casbin.view.ui

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import io.github.will7200.plugins.casbin.CasbinExecutorService


class CasbinToolWindowFactory : ToolWindowFactory {
    // Create the tool window content.
    override fun createToolWindowContent(
        project: Project,
        toolWindow: ToolWindow
    ) {
        val myToolWindow = CasbinExecutorToolWindowImp(project, toolWindow)
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content: Content = contentFactory.createContent(myToolWindow.content, "Main", false)
        toolWindow.contentManager.addContent(content)

        val functionToolWindow = CasbinCustomFunctionsToolWindowImp(project, toolWindow)
        project.service<CasbinExecutorService>().registerCustomFunctionProvider(functionToolWindow)
        val content2: Content = contentFactory.createContent(functionToolWindow.content, "CustomFunctions", false)
        toolWindow.contentManager.addContent(content2)
    }
}
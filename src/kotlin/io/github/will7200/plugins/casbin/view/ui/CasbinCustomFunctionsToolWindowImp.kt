package io.github.will7200.plugins.casbin.view.ui

import com.googlecode.aviator.AviatorEvaluator
import com.googlecode.aviator.exception.ExpressionSyntaxErrorException
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import io.github.will7200.plugins.casbin.CasbinCustomFunctionProvider
import io.github.will7200.plugins.casbin.CasbinExecutorRequest
import io.github.will7200.plugins.casbin.CasbinTopics

class CasbinCustomFunctionsToolWindowImp(private val project: Project, private val toolWindow: ToolWindow) :
    CasbinCustomFunctionProvider, CasbinCustomFunctionWindow(project, toolWindow) {
    private val log: Logger = Logger.getInstance(CasbinCustomFunctionsToolWindowImp::class.java)

    init {
        setupListeners()

    }

    private fun setupListeners() {
        editor.run {
            document.addDocumentListener(object : DocumentListener {
                override fun documentChanged(event: com.intellij.openapi.editor.event.DocumentEvent) {
                    try {
                        AviatorEvaluator.validate(document.text)
                        project.messageBus.syncPublisher(CasbinTopics.EXECUTOR_REQUEST_TOPIC).processRequest(
                            CasbinExecutorRequest.CasbinUpdateCustomFunctions(
                                document.text
                            )
                        )
                    } catch (exception: ExpressionSyntaxErrorException) {
                        log.warn(exception)
                    }
                }
            })
        }
    }

    override fun getScript(): String {
        return editor.document.text
    }
}
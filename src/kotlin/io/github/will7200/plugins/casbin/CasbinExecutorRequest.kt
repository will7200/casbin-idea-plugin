package io.github.will7200.plugins.casbin

import com.intellij.openapi.editor.impl.TextRangeInterval
import com.intellij.openapi.editor.markup.MarkupModel

open class CasbinExecutorRequest {
    enum class Decision {
        ALLOW,
        DENY,
        ERROR,
        INVALID
    }

    open class CasbinEnforcementRequest(
        val modelFile: String,
        val policyFile: String,
        open val rvals: Array<Any>
    ) : CasbinExecutorRequest() {
        var lineNumber: Int? = null
        var result: Decision? = null
        var message: String? = null
        var processed: Boolean = false
        var model: MarkupModel? = null
        var textRange: TextRangeInterval? = null
    }

    open class CasbinCreateEnforcer(
        val modelFile: String,
        val policyFile: String
    ) : CasbinExecutorRequest()

    open class CasbinFileChangeRequest(
        open val checkSum: String
    ) : CasbinExecutorRequest()

    open class CasbinFileChangeNotify(
        open val filePath: String
    ) : CasbinExecutorRequest() {
        var enforcerSwapped: Boolean = false
    }

    open class CasbinModelFileChangeRequest(
        modelFile: String,
        override val checkSum: String
    ) : CasbinFileChangeRequest(modelFile) {

    }

    open class CasbinPolicyFileChangeRequest(
        policyFile: String,
        override val checkSum: String
    ) : CasbinFileChangeRequest(policyFile) {

    }

    open class CasbinUpdateCustomFunctions(
        open val aviatorScript: String
    ) : CasbinExecutorRequest()
}

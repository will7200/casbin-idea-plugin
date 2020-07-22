package io.github.will7200.plugins.casbin

import com.intellij.openapi.editor.impl.TextRangeInterval
import com.intellij.openapi.editor.markup.MarkupModel

open class CasbinExecutorRequest {
    enum class Decision {
        ALLOW,
        DENY,
        ERROR
    }

    open class CasbinEnforcementRequest(
        val modelFile: String,
        val policyFile: String,
        open val rvals: Array<String>
    ) : CasbinExecutorRequest() {
        var lineNumber: Int? = null
        var result: Decision? = null
        var processed: Boolean = false
        var model: MarkupModel? = null
        var textRange: TextRangeInterval? = null
    }

    open class CasbinFileChangeRequest(
        open val checkSum: String
    ) : CasbinExecutorRequest()

    open class CasbinFileChangeNotify(
        open val filePath: String
    ) : CasbinExecutorRequest()

    open class CasbinModelFileChangeRequest(
        val modelFile: String,
        override val checkSum: String
    ) : CasbinFileChangeRequest(checkSum) {

    }

    open class CasbinPolicyFileChangeRequest(
        val policyFile: String,
        override val checkSum: String
    ) : CasbinFileChangeRequest(checkSum) {

    }
}

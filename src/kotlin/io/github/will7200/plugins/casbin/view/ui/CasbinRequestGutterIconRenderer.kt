package io.github.will7200.plugins.casbin.view.ui

import com.intellij.icons.AllIcons
import com.intellij.openapi.editor.markup.GutterIconRenderer
import io.github.will7200.plugins.casbin.CasbinExecutorRequest
import javax.swing.Icon


class CasbinRequestGutterIconRenderer(private val ce: CasbinExecutorRequest.CasbinEnforcementRequest) :
    GutterIconRenderer() {
    override fun getIcon(): Icon {
        if (ce.result == CasbinExecutorRequest.Decision.ALLOW) {
            return AllIcons.Actions.Commit
        }
        return AllIcons.Vcs.Remove
    }

    override fun equals(other: Any?): Boolean {
        if (other is CasbinExecutorRequest.CasbinEnforcementRequest) {
            return ce == other
        }
        return false
    }

    override fun getTooltipText(): String? {
        return ce.result.toString()
    }

    override fun hashCode(): Int {
        return ce.hashCode()
    }
}

package io.github.will7200.plugins.casbin.view.ui

import com.intellij.icons.AllIcons
import com.intellij.openapi.editor.markup.GutterIconRenderer
import io.github.will7200.plugins.casbin.CasbinExecutorRequest
import javax.swing.Icon


class CasbinRequestGutterIconRenderer(private val ce: CasbinExecutorRequest.CasbinEnforcementRequest) :
    GutterIconRenderer() {
    override fun getIcon(): Icon {
        return when (ce.result) {
            CasbinExecutorRequest.Decision.ALLOW -> AllIcons.Actions.Commit
            CasbinExecutorRequest.Decision.ERROR -> AllIcons.General.Error
            CasbinExecutorRequest.Decision.DENY -> AllIcons.Vcs.Remove
            else -> {
                return AllIcons.Debugger.Question_badge
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is CasbinExecutorRequest.CasbinEnforcementRequest) {
            return ce == other
        }
        return false
    }

    override fun getTooltipText(): String? {
        return when {
            ce.message != null -> ce.message
            else -> ce.result.toString()
        }
    }

    override fun hashCode(): Int {
        return ce.hashCode()
    }
}

package io.github.will7200.plugins.casbin.language.actions

import com.intellij.codeInsight.intention.impl.BaseIntentionAction
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.pom.Navigatable
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.util.IncorrectOperationException
import io.github.will7200.plugins.casbin.language.psi.CasbinElementFactory
import io.github.will7200.plugins.casbin.language.psi.CasbinElementTypes
import io.github.will7200.plugins.casbin.language.psi.CasbinObject
import io.github.will7200.plugins.casbin.language.psi.CasbinPsiFile


class CasbinCreateAttributeQuickFix(private val prerequisite: PsiElement) : BaseIntentionAction() {
    private val log: Logger = Logger.getInstance(CasbinCreateAttributeQuickFix::class.java)
    override fun getText(): String {
        return "Create attribute for object"
    }

    override fun getFamilyName(): String {
        return "Casbin object"
    }

    override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?): Boolean {
        return true
    }

    @Throws(IncorrectOperationException::class)
    override fun invoke(project: Project, editor: Editor?, psiFile: PsiFile?) {
        WriteCommandAction.runWriteCommandAction(project) ThrowableRunnable@{
            val file = psiFile as CasbinPsiFile
            val property = CasbinElementFactory.createAttributeName(project, prerequisite.text)
            val anchor =
                file.properties.firstOrNull { it.name == (prerequisite.parent as CasbinObject).name }?.optionValues?.valueTuple
                    ?: return@ThrowableRunnable
            anchor.node.addLeaf(CasbinElementTypes.COMMA, ",", anchor.node.treeNext)
            anchor.node.addLeaf(TokenType.WHITE_SPACE, " ", anchor.node.treeNext)
            anchor.node.addChild(property.node, anchor.node.treeNext)
            (property.lastChild.navigationElement as Navigatable).navigate(true)
        }
    }
}
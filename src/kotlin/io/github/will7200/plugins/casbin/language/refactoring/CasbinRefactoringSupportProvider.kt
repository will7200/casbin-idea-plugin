package io.github.will7200.plugins.casbin.language.refactoring

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.PsiElement
import io.github.will7200.plugins.casbin.language.psi.CasbinFunction

class CasbinRefactoringSupportProvider : RefactoringSupportProvider() {
    private val log: Logger = Logger.getInstance(CasbinRefactoringSupportProvider::class.java)
    override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean {
        return element is CasbinFunction
    }
}
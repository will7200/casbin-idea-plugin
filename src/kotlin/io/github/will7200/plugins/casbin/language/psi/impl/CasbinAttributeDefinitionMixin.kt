package io.github.will7200.plugins.casbin.language.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.util.IncorrectOperationException
import io.github.will7200.plugins.casbin.language.psi.CasbinAttributeDefinition
import io.github.will7200.plugins.casbin.language.psi.CasbinElementFactory


abstract class CasbinAttributeDefinitionMixin(node: ASTNode) : ASTWrapperPsiElement(node), CasbinAttributeDefinition, PsiNamedElement {
    private val log: Logger = Logger.getInstance(CasbinAttributeDefinitionMixin::class.java)
    override fun getName(): String? {
        return node.text
    }

    @Throws(IncorrectOperationException::class)
    override fun setName(name: String): PsiElement {
        val identifier = node
        val attributeName = CasbinElementFactory.createAttributeName(this.project, name)
        node.replaceChild(identifier, attributeName.node)
        return this
    }
}
package io.github.will7200.plugins.casbin.language.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.PsiNamedElement
import com.intellij.util.IncorrectOperationException
import io.github.will7200.plugins.casbin.language.psi.CasbinElementFactory
import io.github.will7200.plugins.casbin.language.psi.CasbinElementTypes

abstract class CasbinPropertyMixin(node: ASTNode) : ASTWrapperPsiElement(node), PsiNamedElement {
    private val log: Logger = Logger.getInstance(CasbinPropertyMixin::class.java)
    override fun getName(): String? {
        val functionNode = node.findChildByType(CasbinElementTypes.FLAT_KEY) ?: return null
        return functionNode.text
    }

    @Throws(IncorrectOperationException::class)
    override fun setName(name: String): PsiNamedElement {
        val identifier = node.findChildByType(CasbinElementTypes.FLAT_KEY) ?: return this
        val functionName = CasbinElementFactory.createPropertyName(this.project, name)
        node.replaceChild(identifier, functionName.node)
        return this
    }

//    override fun getNameIdentifier(): PsiElement? {
//        val keyNode: @Nullable ASTNode? = node.findChildByType(CasbinElementTypes.FLAT_KEY)
//        return keyNode?.psi
//    }
}
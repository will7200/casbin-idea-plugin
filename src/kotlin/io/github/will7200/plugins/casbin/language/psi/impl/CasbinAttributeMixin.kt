package io.github.will7200.plugins.casbin.language.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.util.IncorrectOperationException
import io.github.will7200.plugins.casbin.language.psi.CasbinAttribute
import io.github.will7200.plugins.casbin.language.psi.CasbinElementFactory
import io.github.will7200.plugins.casbin.language.reference.CasbinAttributeReference


abstract class CasbinAttributeMixin(node: ASTNode) : ASTWrapperPsiElement(node), CasbinAttribute {
    private val log: Logger = Logger.getInstance(CasbinAttributeMixin::class.java)
    override fun getName(): String? {
        return node.text
    }

    @Throws(IncorrectOperationException::class)
    fun setName(name: String): PsiElement {
        val identifier = node
        val attributeName = CasbinElementFactory.createAttributeName(this.project, name)
        node.replaceChild(identifier, attributeName.node)
        return this
    }

    override fun getReference(): PsiReference? {
        if (myReference.isNotEmpty()) {
            return myReference[0]
        }
        return null
    }

    override fun getReferences(): Array<PsiReference> = myReference
    private val myReference by lazy {
        arrayOf<PsiReference>(
            CasbinAttributeReference(
                this
            )
        )
    }
}
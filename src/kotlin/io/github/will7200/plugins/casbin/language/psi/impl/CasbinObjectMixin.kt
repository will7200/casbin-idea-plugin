package io.github.will7200.plugins.casbin.language.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.util.IncorrectOperationException
import io.github.will7200.plugins.casbin.language.psi.CasbinElementFactory
import io.github.will7200.plugins.casbin.language.psi.CasbinElementTypes
import io.github.will7200.plugins.casbin.language.psi.CasbinObject
import io.github.will7200.plugins.casbin.language.reference.CasbinObjectReference

abstract class CasbinObjectMixin(node: ASTNode) : ASTWrapperPsiElement(node), CasbinObject {
    private val log: Logger = Logger.getInstance(CasbinObjectMixin::class.java)
    override fun getName(): String? {
        val functionNode = node.findChildByType(CasbinElementTypes.OBJECT_IDENTIFIER) ?: return null
        return functionNode.text
    }

    @Throws(IncorrectOperationException::class)
    fun setName(name: String): PsiElement {
        val identifier = node.findChildByType(CasbinElementTypes.OBJECT_IDENTIFIER) ?: return this
        val functionName = CasbinElementFactory.createObjectName(this.project, name)
        node.replaceChild(identifier, functionName.node)
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
            CasbinObjectReference(
                this
            )
        )
    }
}
package io.github.will7200.plugins.casbin.language.reference

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.lang.ASTNode
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiReference
import io.github.will7200.plugins.casbin.language.psi.*
import io.github.will7200.plugins.casbin.language.psi.impl.CasbinFunctionMixin

class CasbinPropertyReference(private val usage: CasbinFunctionMixin)  : PsiReference {
    private val log: Logger = Logger.getInstance(CasbinPropertyReference::class.java)
    override fun getElement() = usage
    override fun getRangeInElement(): TextRange {
        val startOffset = nameNode.startOffset - usage.node.startOffset
        return TextRange.create(startOffset, startOffset + nameNode.textLength)
    }
    override fun bindToElement(element: PsiElement): PsiElement? = null
    private val nameNode: ASTNode
        get() {
            return usage.functionName.node
        }


    override fun isReferenceTo(element: PsiElement): Boolean {
        if (element is CasbinProperty) {
            return element.key == nameNode.text
        }
        return false
    }

    override fun getCanonicalText() = nameNode.text

    override fun handleElementRename(newName: String): PsiElement {
        usage.setName(newName)
        return usage
    }

    override fun isSoft() = true

    override fun getVariants()
            = (usage.containingFile as CasbinPsiFile).properties.distinctBy { it.text }.map {
        LookupElementBuilder.create(it)
    }.toTypedArray()

    override fun resolve(): PsiElement? {
        return (usage.containingFile as CasbinPsiFile).properties
            .filter { it.key == nameNode.text }
            .map(::PsiElementResolveResult)
            .firstOrNull()?.element
    }
}
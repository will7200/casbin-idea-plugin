package io.github.will7200.plugins.casbin.language.reference

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.lang.ASTNode
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiReference
import io.github.will7200.plugins.casbin.language.psi.CasbinAttribute
import io.github.will7200.plugins.casbin.language.psi.CasbinAttributeDefinition
import io.github.will7200.plugins.casbin.language.psi.CasbinProperty
import io.github.will7200.plugins.casbin.language.psi.CasbinPsiFile
import io.github.will7200.plugins.casbin.language.psi.impl.CasbinAttributeMixin
import io.github.will7200.plugins.casbin.language.psi.impl.CasbinObjectMixin

open class CasbinAttributeReference(private val usage: CasbinAttributeMixin) :
    PsiReference {
    private val log: Logger = Logger.getInstance(CasbinAttributeReference::class.java)

    override fun getElement() = usage as CasbinAttribute
    override fun getRangeInElement(): TextRange {
        val startOffset = nameNode.startOffset - usage.node.startOffset
        return TextRange.create(startOffset, startOffset + nameNode.textLength)
    }

    override fun bindToElement(element: PsiElement): PsiElement? = null
    private val nameNode: ASTNode
        get() {
            return usage.node
        }

    override fun isReferenceTo(element: PsiElement): Boolean {
        if (element is CasbinAttributeDefinition) {
            val obj = (usage.parent as CasbinObjectMixin)
            if (obj.name == (element.parent.parent.parent as CasbinProperty).name) {
                return true
            }
            return false
        }
        return false
    }

    override fun getCanonicalText() = nameNode.text

    override fun handleElementRename(newName: String): PsiElement {
        usage.setName(newName)
        return usage
    }

    override fun isSoft() = true

    override fun getVariants(): Array<Any> {
        return (usage.containingFile as CasbinPsiFile).attributesForProperty((usage.parent as CasbinObjectMixin).name!!)
            .distinctBy { it.name }.map {
                LookupElementBuilder.create(it.name!!).withTypeText("attribute")
            }.toTypedArray()
    }


    override fun resolve(): PsiElement? {
        return (usage.containingFile as CasbinPsiFile).attributesForProperty((usage.parent as CasbinObjectMixin).name!!)
            .filter {
                it.name == (nameNode.text)
            }
            .map(::PsiElementResolveResult)
            .firstOrNull()?.element
    }
}
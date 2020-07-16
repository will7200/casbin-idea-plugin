package io.github.will7200.plugins.casbin.language.reference

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.lang.ASTNode
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.util.parentOfType
import com.intellij.psi.util.parentOfTypes
import io.github.will7200.plugins.casbin.language.psi.*
import io.github.will7200.plugins.casbin.language.psi.impl.CasbinAttributeMixin
import io.github.will7200.plugins.casbin.language.psi.impl.CasbinObjectMixin
import org.jetbrains.annotations.Nullable
import java.util.*

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
            log.warn(usage.node.text)
            return usage.node
        }

    override fun isReferenceTo(element: PsiElement): Boolean {
        log.warn(element.toString() + " " + element.text + " " + element.parent.toString())
        if (element is CasbinAttributeDefinition) {
            val obj = (usage.parent as CasbinObjectMixin)
            if (obj.name == (element.parent.parent.parent as CasbinProperty).name) {
                return true
            }
            return false
        }
        if (element is CasbinProperty) {
            val properties: @Nullable CasbinOptionValueList? = element.optionValues.optionValueList ?: return false
            if (properties != null) {
                for (attribute in properties.attributeDefinitionList) {
                    if (attribute.text == nameNode.text) {
                        return true
                    }
                }
                return false
            } else {
                return false
            }
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
package io.github.will7200.plugins.casbin.language.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.util.IncorrectOperationException
import com.intellij.util.xml.model.gotosymbol.GoToSymbolProvider
import io.github.will7200.plugins.casbin.language.CasbinFileIcon
import io.github.will7200.plugins.casbin.language.psi.CasbinAttributeDefinition
import io.github.will7200.plugins.casbin.language.psi.CasbinElementFactory
import javax.swing.Icon


abstract class CasbinAttributeDefinitionMixin(node: ASTNode) : ASTWrapperPsiElement(node), CasbinAttributeDefinition,
    PsiNamedElement {
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

    override fun getPresentation(): ItemPresentation? {
        return object : ItemPresentation {
            override fun getPresentableText(): String? {
                return "[attribute] ${this@CasbinAttributeDefinitionMixin.name}"
            }

            override fun getLocationString(): String? {
                return this@CasbinAttributeDefinitionMixin.containingFile.name.replace(
                    this@CasbinAttributeDefinitionMixin.containingFile.containingDirectory.name,
                    ""
                )
            }

            override fun getIcon(unused: Boolean): Icon? {
                return CasbinFileIcon
            }
        }
    }

    fun createLabelNavigationItem(element: PsiElement): NavigationItem? {
        return GoToSymbolProvider.BaseNavigationItem(
            element,
            presentation?.presentableText!!,
            CasbinFileIcon
        )
    }
}
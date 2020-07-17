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
import io.github.will7200.plugins.casbin.language.psi.CasbinElementFactory
import io.github.will7200.plugins.casbin.language.psi.CasbinElementTypes
import io.github.will7200.plugins.casbin.language.psi.CasbinProperty
import javax.swing.Icon

abstract class CasbinPropertyMixin(node: ASTNode) : ASTWrapperPsiElement(node), PsiNamedElement, CasbinProperty {
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

    override fun getPresentation(): ItemPresentation? {
        return object : ItemPresentation {
            override fun getPresentableText(): String? {
                return "[property] ${this@CasbinPropertyMixin.key}"
            }

            override fun getLocationString(): String? {
                return this@CasbinPropertyMixin.containingFile.name.replace(
                    this@CasbinPropertyMixin.containingFile.containingDirectory.name,
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
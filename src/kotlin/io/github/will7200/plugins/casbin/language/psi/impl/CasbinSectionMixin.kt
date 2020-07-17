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
import io.github.will7200.plugins.casbin.language.psi.CasbinSection
import javax.swing.Icon

abstract class CasbinSectionMixin(node: ASTNode) : ASTWrapperPsiElement(node), PsiNamedElement, CasbinSection {
    private val log: Logger = Logger.getInstance(CasbinSectionMixin::class.java)
    override fun getName(): String? {
        val functionNode =
            node.findChildByType(CasbinElementTypes.HEADER)?.findChildByType(CasbinElementTypes.SECTION_NAME)
                ?: return null
        return functionNode.text
    }

    @Throws(IncorrectOperationException::class)
    override fun setName(name: String): PsiNamedElement {
        val identifier =
            node.findChildByType(CasbinElementTypes.HEADER)?.findChildByType(CasbinElementTypes.SECTION_NAME)
                ?: return this
        val functionName = CasbinElementFactory.createSectionName(this.project, name)
        node.replaceChild(identifier, functionName.node)
        return this
    }

    override fun getPresentation(element: CasbinSection): ItemPresentation? {
        return object : ItemPresentation {
            override fun getPresentableText(): String? {
                return "[section] ${element.name}"
            }

            override fun getLocationString(): String? {
                return element.containingFile.name.replace(element.containingFile.containingDirectory.name, "")
            }

            override fun getIcon(unused: Boolean): Icon? {
                return CasbinFileIcon
            }
        }
    }

    fun createLabelNavigationItem(element: PsiElement): NavigationItem? {
        return GoToSymbolProvider.BaseNavigationItem(
            element,
            getPresentation(element as CasbinSection)?.presentableText!!,
            CasbinFileIcon
        )
    }
}
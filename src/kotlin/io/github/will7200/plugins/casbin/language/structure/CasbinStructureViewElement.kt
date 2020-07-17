package io.github.will7200.plugins.casbin.language.structure

import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import io.github.will7200.plugins.casbin.language.psi.CasbinProperty
import io.github.will7200.plugins.casbin.language.psi.CasbinPsiFile
import io.github.will7200.plugins.casbin.language.psi.CasbinSection

class CasbinStructureViewElement(private val element: PsiElement) : StructureViewTreeElement, SortableTreeElement {
    private val log: Logger = Logger.getInstance(CasbinStructureViewElement::class.java)
    override fun getPresentation(): ItemPresentation {
        if (element is CasbinPsiFile) return CasbinFilePresentation(element)
        return (element as NavigationItem).presentation!!
    }

    override fun getChildren(): Array<out TreeElement> {
        return when (element) {
            is CasbinPsiFile -> element.sections.map(::CasbinStructureViewElement).toTypedArray()
            is CasbinSection -> element.propertyList.map(::CasbinStructureViewElement).toTypedArray()
            is CasbinProperty -> {
                if (element.optionValues.valueTuple != null) {
                    return element.optionValues.valueTuple!!.attributeDefinitionList.map(::CasbinStructureViewElement)
                        .toTypedArray()
                }
                return emptyArray()
            }
            else -> emptyArray()
        }
    }

    override fun getAlphaSortKey() = when (element) {
        is PsiFile -> element.name.toLowerCase()
        else -> element.text.toLowerCase()
    }

    override fun canNavigate() = (element as? NavigationItem)?.canNavigate() ?: false
    override fun canNavigateToSource() = (element as? NavigationItem)?.canNavigateToSource() ?: false

    override fun navigate(requestFocus: Boolean) {
        (element as? NavigationItem)?.navigate(requestFocus)
    }


    override fun getValue() = element
}

package io.github.will7200.plugins.casbin.language.psi

import com.intellij.psi.PsiElement

object CasbinPsiUtils {
    @JvmStatic
    fun getName(element: CasbinSectionName): String? {
        val sectionNode = element.node.findChildByType(CasbinElementTypes.SECTION_IDENTIFER)
        return sectionNode?.text
    }

    @JvmStatic
    fun setName(element: CasbinSectionName, newName: String): PsiElement {
        val identifier = element.node.findChildByType(CasbinElementTypes.SECTION_IDENTIFER) ?: return element
        val sectionName = CasbinElementFactory.createSectionName(element.project, newName)
        element.node.replaceChild(identifier, sectionName)
        return element
    }

    @JvmStatic
    fun getKey(element: CasbinProperty): String? {
        val keyNode = element.node.findChildByType(CasbinElementTypes.IDENTIFIER)
        return keyNode?.text
    }

    @JvmStatic
    fun getValue(element: CasbinProperty): String? {
        val keyNode = element.node.findChildByType(CasbinElementTypes.OPTION_VALUES)
        return keyNode?.text
    }
}
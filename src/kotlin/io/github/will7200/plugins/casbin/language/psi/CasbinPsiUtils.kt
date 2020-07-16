package io.github.will7200.plugins.casbin.language.psi

import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.PsiElement

object CasbinPsiUtils {

    private val log: Logger = Logger.getInstance(CasbinPsiUtils::class.java)

    @JvmStatic
    fun getName(element: CasbinSection): String? {
        val sectionNode = element.node.findChildByType(CasbinElementTypes.HEADER)?.findChildByType(CasbinElementTypes.SECTION_NAME)
        return sectionNode?.text
    }

    @JvmStatic
    fun setName(element: CasbinSection, newName: String): PsiElement {
        val identifier = element.node.findChildByType(CasbinElementTypes.HEADER)?.findChildByType(CasbinElementTypes.SECTION_NAME) ?: return element
        val sectionName = CasbinElementFactory.createSectionName(element.project, newName)
        element.node.replaceChild(identifier, sectionName)
        return element
    }

    @JvmStatic
    fun getKey(element: CasbinProperty): String? {
        val keyNode = element.node.findChildByType(CasbinElementTypes.FLAT_KEY)
        return keyNode?.text
    }

    @JvmStatic
    fun getValue(element: CasbinProperty): String? {
        val keyNode = element.node.findChildByType(CasbinElementTypes.OPTION_VALUES)
        return keyNode?.text
    }
}
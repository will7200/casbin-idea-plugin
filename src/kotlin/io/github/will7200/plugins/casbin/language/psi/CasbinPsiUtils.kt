package io.github.will7200.plugins.casbin.language.psi

import com.intellij.openapi.diagnostic.Logger

object CasbinPsiUtils {

    private val log: Logger = Logger.getInstance(CasbinPsiUtils::class.java)

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
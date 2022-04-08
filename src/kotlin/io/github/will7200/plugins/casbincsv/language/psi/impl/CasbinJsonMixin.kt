package io.github.will7200.plugins.casbincsv.language.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.diagnostic.Logger
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVElementTypes
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVJson
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVJsonProp

abstract class CasbinJsonMixin(node: ASTNode) : ASTWrapperPsiElement(node), CasbinCSVJson {
    private val log: Logger = Logger.getInstance(this::class.java)
    fun isArray(): Boolean {
        return node.elementType == CasbinCSVElementTypes.JSON_ARRAY
    }

    fun isObject(): Boolean {
        return node.elementType == CasbinCSVElementTypes.JSON_OBJECT
    }

    override fun iterateAttributes(): List<CasbinCSVJsonProp> {
        when (this) {
            is CasbinCSVJsonObjectImpl -> {
                return this.jsonPropList
            }
        }
        return emptyList()
    }
}
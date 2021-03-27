package io.github.will7200.plugins.casbin.language.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.diagnostic.Logger
import io.github.will7200.plugins.casbin.language.psi.CasbinSubAttribute

abstract class CasbinSubAttributeMixin(node: ASTNode) : ASTWrapperPsiElement(node), CasbinSubAttribute {
    private val log: Logger = Logger.getInstance(CasbinSubAttributeMixin::class.java)
    override fun getName(): String? {
        return node.text
    }
}
package io.github.will7200.plugins.casbin.language.reference

import com.intellij.openapi.diagnostic.Logger
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.psi.*
import com.intellij.util.ProcessingContext
import io.github.will7200.plugins.casbin.language.psi.CasbinFunction
import io.github.will7200.plugins.casbin.language.psi.impl.CasbinFunctionMixin


class CasbinReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        // val function = psiElement(CasbinFunction::class.java)
        // registrar.registerReferenceProvider(function, CasbinFunctions)
    }
}

object CasbinFunctions : PsiReferenceProvider() {
    private val log: Logger = Logger.getInstance(CasbinFunctions::class.java)
    override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<out PsiReference> {
        log.warn(element.toString())
        if (element !is CasbinFunctionMixin) return PsiReference.EMPTY_ARRAY
        return arrayOf(CasbinFunctionReference(element))
    }
}
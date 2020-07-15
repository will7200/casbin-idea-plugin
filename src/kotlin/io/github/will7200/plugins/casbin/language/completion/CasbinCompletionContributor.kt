package io.github.will7200.plugins.casbin.language.completion

import com.intellij.codeInsight.completion.*
import com.intellij.openapi.diagnostic.Logger
import com.intellij.patterns.PatternCondition
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.util.parentOfTypes
import com.intellij.util.ProcessingContext
import io.github.will7200.plugins.casbin.language.psi.CasbinProperty
import io.github.will7200.plugins.casbin.language.psi.CasbinSection

open class CasbinCompletionContributor : CompletionContributor() {
    private val afterEqualsProperty = psiElement().afterLeaf("=").inside(CasbinProperty::class.java)
    private val log: Logger = Logger.getInstance(CasbinCompletionContributor::class.java)

    init {
        // policy_effect suggestions
        extend(
            CompletionType.BASIC,
            psiElement().afterLeaf("=")
                .with(object : PatternCondition<PsiElement>(null) {
                    override fun accepts(psiElement: PsiElement, context: ProcessingContext): Boolean {
                        val sectionName =
                            psiElement.parentOfTypes(CasbinProperty::class)?.parentOfTypes(CasbinSection::class)
                                ?: return false
                        if (sectionName.name == "policy_effect") {
                            return true
                        }
                        return false
                    }
                }),
            CasbinPolicyEffectSuggestions
        )
    }

}
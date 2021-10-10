package io.github.will7200.plugins.casbin.language.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext

object CasbinPolicyEffectSuggestions : CompletionProvider<CompletionParameters>() {
    // examples are pulled from https://casbin.org/docs/en/syntax-for-models
    private val policiesEffect = arrayOf(
        Pair("allow-override", "some(where (p.eft == allow))"),
        Pair("deny-override", "!some(where (p.eft == deny))"),
        Pair("allow-and-deny", "some(where (p.eft == allow)) && !some(where (p.eft == deny))"),
        Pair("priority", "priority(p.eft) || deny"),
        Pair("priority-based-on-role", "subjectPriority(p.eft)")
    )

    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        result.addAllElements(policiesEffect.map { p: Pair<String, String> ->
            LookupElementBuilder.create(p.second).withTypeText(p.first)
        })
    }
}
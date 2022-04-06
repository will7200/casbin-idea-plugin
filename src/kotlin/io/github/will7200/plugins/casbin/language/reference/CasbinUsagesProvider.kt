package io.github.will7200.plugins.casbin.language.reference

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.tree.TokenSet
import io.github.will7200.plugins.casbin.language.lexer.CasbinLexerAdapter
import io.github.will7200.plugins.casbin.language.psi.CasbinElementTypes

class CasbinUsagesProvider : FindUsagesProvider {
    override fun getWordsScanner(): WordsScanner? {
        return DefaultWordsScanner(
            CasbinLexerAdapter(),
            // Identifiers.
            TokenSet.create(
                CasbinElementTypes.FUNCTION_NAME, CasbinElementTypes.OBJECT_IDENTIFIER, CasbinElementTypes.ATTRIBUTE_DEFINITION,
                CasbinElementTypes.ATTRIBUTE, CasbinElementTypes.FLAT_KEY
            ),
            // Comments.
            TokenSet.create(
                CasbinElementTypes.LINE_COMMENT
            ),
            // Literals.
            TokenSet.create(
                CasbinElementTypes.STRINGS
            )
        )
    }

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
        return element.node.text
    }

    override fun getDescriptiveName(element: PsiElement): String {
        return element.text
    }

    override fun getType(element: PsiElement): String {
        return element.node.elementType.toString().toLowerCase()
    }

    override fun getHelpId(psiElement: PsiElement): String? {
        return null
    }

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
        return psiElement is PsiNamedElement
    }
}
package io.github.will7200.plugins.casbin.language.highlighting

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import io.github.will7200.plugins.casbin.language.lexer.CasbinLexerAdapter
import io.github.will7200.plugins.casbin.language.psi.CasbinElementTypes.*;

class CasbinHighlighter : SyntaxHighlighterBase() {
    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        return tokenHighlights.getOrElse(tokenType, { emptyArray() })
    }

    override fun getHighlightingLexer(): Lexer {
        return CasbinLexerAdapter()
    }

    companion object {
        private val tokenHighlights = mapOf(
            L_BRACKET to arrayOf(CasbinHighlighterColors.Brackets),
            R_BRACKET to arrayOf(CasbinHighlighterColors.Brackets),
            SECTION_IDENTIFER to arrayOf(CasbinHighlighterColors.SectionName),
            IDENTIFIER to arrayOf(CasbinHighlighterColors.Identifier),
            ALLOW to arrayOf(CasbinHighlighterColors.Keyword),
            DENY to arrayOf(CasbinHighlighterColors.Keyword),
            DOT to arrayOf(CasbinHighlighterColors.KeyValueSeparator)
        )
    }
}
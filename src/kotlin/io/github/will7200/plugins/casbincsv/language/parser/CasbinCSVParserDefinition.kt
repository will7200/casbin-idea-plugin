package io.github.will7200.plugins.casbincsv.language.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import io.github.will7200.plugins.casbin.language.psi.CasbinElementTypes
import io.github.will7200.plugins.casbincsv.language.CasbinCSVLanguage
import io.github.will7200.plugins.casbincsv.language.lexer.CasbinCSVEscapeCharacter
import io.github.will7200.plugins.casbincsv.language.lexer.CasbinCSVLexerAdapter
import io.github.will7200.plugins.casbincsv.language.lexer.CasbinCSVValueSeparator
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVElementTypes
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVPsiFile

class CasbinCSVParserDefinition : ParserDefinition {
    override fun createLexer(project: Project) =
        CasbinCSVLexerAdapter(CasbinCSVValueSeparator.COMMA, CasbinCSVEscapeCharacter.QUOTE)

    override fun createParser(project: Project): PsiParser = CasbinCSVParser()

    override fun getCommentTokens() = COMMENTS
    override fun getWhitespaceTokens() = WHITE_SPACES
    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY
    override fun getFileNodeType() = FILE

    override fun createFile(viewProvider: FileViewProvider): PsiFile = CasbinCSVPsiFile(viewProvider)
    override fun createElement(node: ASTNode): PsiElement = CasbinCSVElementTypes.Factory.createElement(node)

    private companion object {
        val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
        val COMMENTS = TokenSet.create(CasbinElementTypes.LINE_COMMENT)
        val FILE = IFileElementType(CasbinCSVLanguage)
    }
}
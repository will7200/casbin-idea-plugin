package io.github.will7200.plugins.casbin.language.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.PsiParser
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import com.intellij.lang.ParserDefinition
import io.github.will7200.plugins.casbin.language.CasbinLanguage
import io.github.will7200.plugins.casbin.language.lexer.CasbinLexerAdapter
import io.github.will7200.plugins.casbin.language.psi.CasbinElementTypes
import io.github.will7200.plugins.casbin.language.psi.CasbinPsiFile

class CasbinParserDefinition : ParserDefinition {
    override fun createLexer(project: Project) = CasbinLexerAdapter()
    override fun createParser(project: Project): PsiParser = CasbinParser()

    override fun getCommentTokens() = COMMENTS
    override fun getWhitespaceTokens() = WHITE_SPACES
    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY
    override fun getFileNodeType() = FILE

    override fun createFile(viewProvider: FileViewProvider): PsiFile = CasbinPsiFile(viewProvider)
    override fun createElement(node: ASTNode): PsiElement = CasbinElementTypes.Factory.createElement(node)

    private companion object {
        val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
        val COMMENTS = TokenSet.create(CasbinElementTypes.LINE_COMMENT)
        val FILE = IFileElementType(CasbinLanguage)
    }
}
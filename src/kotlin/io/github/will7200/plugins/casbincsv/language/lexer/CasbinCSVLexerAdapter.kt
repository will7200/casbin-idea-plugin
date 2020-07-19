package io.github.will7200.plugins.casbincsv.language.lexer

import com.intellij.lexer.FlexAdapter

class CasbinCSVLexerAdapter(separator: CasbinCSVValueSeparator?, escapeCharacter: CasbinCSVEscapeCharacter?) :
    FlexAdapter(_CasbinCSVLexer(null, separator, escapeCharacter))
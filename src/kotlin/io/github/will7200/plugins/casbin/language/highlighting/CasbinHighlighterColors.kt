package io.github.will7200.plugins.casbin.language.highlighting

import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as DLHC

object CasbinHighlighterColors {
    final val Identifier = key("CASBIN_IDENTIFIER", DLHC.IDENTIFIER)
    final val BadCharacter = key("Casbin_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)
    final val HashComment = key("Casbin_HASH_COMMENT", DLHC.LINE_COMMENT)
    final val DoubleSlashComment = key("Casbin_DOUBLE_SLASH_COMMENT", DLHC.LINE_COMMENT)
    final val Null = key("Casbin_NULL", DLHC.KEYWORD)
    final val Boolean = key("Casbin_BOOLEAN", DLHC.KEYWORD)
    final val Number = key("Casbin_NUMBER", DLHC.NUMBER)
    final val QuotedString = key("Casbin_QUOTED_STRING", DLHC.STRING)
    final val MultilineString = key("Casbin_MULTILINE_STRING", DLHC.STRING)
    final val ValidStringEscape = key("Casbin_VALID_STRING_ESCAPE", DLHC.VALID_STRING_ESCAPE)
    final val InvalidStringEscape = key("Casbin_INVALID_STRING_ESCAPE", DLHC.INVALID_STRING_ESCAPE)
    final val Brackets = key("Casbin_BRACKETS", DLHC.BRACKETS)
    final val Braces = key("Casbin_OBJECT_BRACES", DLHC.BRACES)
    final val IncludeModifierParens = key("Casbin_INCLUDE_MODIFIER_PARENS", DLHC.PARENTHESES)
    final val SubBraces = key("Casbin_SUBSTITUTION_BRACES", DLHC.BRACES)
    final val KeyValueSeparator = key("Casbin_KEY_VALUE_SEPARATOR", DLHC.OPERATION_SIGN)
    final val Comma = key("Casbin_COMMA", DLHC.COMMA)
    final val Include = key("Casbin_INCLUDE", DLHC.KEYWORD)
    final val IncludeModifier = key("Casbin_INCLUDE_MODIFIER", DLHC.IDENTIFIER)
    final val SubstitutionSign = key("Casbin_SUBSTITUTION_SIGN", DLHC.OPERATION_SIGN)
    final val OptionalSubstitutionSign = key("Casbin_OPTIONAL_SUBSTITUTION_SIGN", DLHC.OPERATION_SIGN)
    final val UnquotedString = key("Casbin_UNQUOTED_STRING", DLHC.IDENTIFIER)
    final val PathSeparator = key("PATH_SEPARATOR", DLHC.DOT)
    final val EntryKey = key("ENTRY_KEY", DLHC.INSTANCE_METHOD)
    final val SubstitutionKey = key("SUBSTITUTION_KEY", DLHC.INSTANCE_FIELD)
    final val Keyword = key("CASBIN_KEYWORD", DLHC.KEYWORD)
    final val SectionName = key("CASBIN_SECTION_NAME", DLHC.LABEL)
    final val PropertyKey = key("CASBIN_PROPERTY_KEY", DLHC.INSTANCE_FIELD)
    final val PropertyValue = key("CASBIN_PROPERTY_VALUE", DLHC.STRING)

    private fun key(name: String, prototype: TextAttributesKey) =
        TextAttributesKey.createTextAttributesKey(name, prototype)
}
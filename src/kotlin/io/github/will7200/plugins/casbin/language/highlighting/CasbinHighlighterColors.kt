package io.github.will7200.plugins.casbin.language.highlighting

import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as DLHC

object CasbinHighlighterColors {
    final val Identifier = key("CASBIN_IDENTIFIER", DLHC.IDENTIFIER)
    final val Brackets = key("CASBIN_BRACKETS", DLHC.BRACKETS)
    final val Braces = key("CASBIN_OBJECT_BRACES", DLHC.BRACES)
    final val KeyValueSeparator = key("CASBIN_KEY_VALUE_SEPARATOR", DLHC.OPERATION_SIGN)
    final val IncludeModifier = key("CASBIN_INCLUDE_MODIFIER", DLHC.IDENTIFIER)
    final val Keyword = key("CASBIN_KEYWORD", DLHC.KEYWORD)
    final val StringValue = key("CASBIN_STRING_VALUE", DLHC.STRING)
    final val SectionName = key("CASBIN_SECTION_NAME", DLHC.LABEL)
    final val PropertyKey = key("CASBIN_PROPERTY_KEY", DLHC.CLASS_NAME)
    final val PropertyValue = key("CASBIN_PROPERTY_VALUE", DLHC.STRING)
    final val ObjectInstance = key("CASBIN_INSTANCE", DLHC.CLASS_NAME)
    final val Attribute = key("CASBIN_ATTRIBUTE", DLHC.INSTANCE_FIELD)
    final val FunctionName = key("CASBIN_FUNCTION_NAME", DLHC.FUNCTION_CALL)


    private fun key(name: String, prototype: TextAttributesKey) =
        TextAttributesKey.createTextAttributesKey(name, prototype)
}
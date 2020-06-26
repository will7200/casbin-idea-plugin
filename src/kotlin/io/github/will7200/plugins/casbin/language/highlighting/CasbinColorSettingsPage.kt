package io.github.will7200.plugins.casbin.language.highlighting

import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import io.github.will7200.plugins.casbin.language.CasbinFileIcon

class CasbinColorSettingsPage : ColorSettingsPage {
    companion object {

        /*
         * You can group multiple descriptors by prefixing it with the group name and '//'. FYI.
         */
        @JvmStatic
        val DESCRIPTORS = arrayOf(
            AttributesDescriptor("Braces", CasbinHighlighterColors.Braces),
            AttributesDescriptor("Brackets", CasbinHighlighterColors.Brackets),
            AttributesDescriptor("Section Name", CasbinHighlighterColors.SectionName),
            AttributesDescriptor("Identifiers", CasbinHighlighterColors.Identifier),
            AttributesDescriptor("Keywords//allow", CasbinHighlighterColors.Keyword),
            AttributesDescriptor("Keywords//deny", CasbinHighlighterColors.Keyword),
            AttributesDescriptor("Properties//keys", CasbinHighlighterColors.PropertyKey),
            AttributesDescriptor("Properties//values", CasbinHighlighterColors.PropertyValue)
        )

        @JvmStatic
        val ADDITIONAL_DESCRIPTORS = mapOf(
            "propertyKey" to CasbinHighlighterColors.PropertyKey,
            "propertyValue" to CasbinHighlighterColors.PropertyValue
        )
    }

    override fun getIcon() = CasbinFileIcon

    override fun getHighlighter() = CasbinHighlighter()

    override fun getDemoText() = """[request_definition]
r = sub, obj, act

[policy_definition]
p = sub, obj, act

[policy_effect]
e = some(where (<propertyKey>p.eft == allow))

[matchers]
m = <propertyKey>r</propertyKey>.sub == <propertyKey>p</propertyKey>.sub && <propertyKey>r</propertyKey>.obj == <propertyKey>p</propertyKey>.obj && <propertyKey>r</propertyKey>.act == <propertyKey>p</propertyKey>.act
        """.trimMargin()

    override fun getAdditionalHighlightingTagToDescriptorMap() = ADDITIONAL_DESCRIPTORS

    override fun getAttributeDescriptors() = DESCRIPTORS

    override fun getColorDescriptors(): Array<out ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY!!

    override fun getDisplayName() = "Casbin"
}
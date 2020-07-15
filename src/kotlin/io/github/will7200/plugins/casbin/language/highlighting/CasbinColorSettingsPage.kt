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
            AttributesDescriptor("Brackets", CasbinHighlighterColors.Brackets),
            AttributesDescriptor("Section Name", CasbinHighlighterColors.SectionName),
            AttributesDescriptor("Keywords//allow", CasbinHighlighterColors.Keyword),
            AttributesDescriptor("Keywords//deny", CasbinHighlighterColors.Keyword),
            AttributesDescriptor("Properties//keys", CasbinHighlighterColors.PropertyKey),
            AttributesDescriptor("Properties//attributes", CasbinHighlighterColors.Attribute),
            AttributesDescriptor("Properties//objects", CasbinHighlighterColors.ObjectInstance),
            AttributesDescriptor("Properties//functions", CasbinHighlighterColors.FunctionName),
            AttributesDescriptor("Properties//string_values", CasbinHighlighterColors.StringValue)
        )

        @JvmStatic
        val ADDITIONAL_DESCRIPTORS = mapOf(
            "propertyKey" to CasbinHighlighterColors.PropertyKey,
            "propertyValue" to CasbinHighlighterColors.PropertyValue,
            "valueAttribute" to CasbinHighlighterColors.Attribute,
            "objectInstance" to CasbinHighlighterColors.ObjectInstance,
            "functions" to CasbinHighlighterColors.FunctionName,
            "stringValue" to CasbinHighlighterColors.StringValue
        )
    }

    override fun getIcon() = CasbinFileIcon

    override fun getHighlighter() = CasbinHighlighter()

    override fun getDemoText() = """[request_definition]
<propertyKey>r</propertyKey> = <valueAttribute>sub</valueAttribute>, <valueAttribute>obj</valueAttribute>, <valueAttribute>act</valueAttribute>

[policy_definition]
<propertyKey>p</propertyKey> = <valueAttribute>sub</valueAttribute>, <valueAttribute>obj</valueAttribute>, <valueAttribute>act</valueAttribute>

[role_definition]
<propertyKey>g</propertyKey> = <valueAttribute>_</valueAttribute>, <valueAttribute>_</valueAttribute>
<propertyKey>g2</propertyKey> = <valueAttribute>_</valueAttribute>, <valueAttribute>_</valueAttribute>

[policy_effect]
<propertyKey>e</propertyKey> = <functions>some</functions>(<functions>where</functions> (<objectInstance>p</objectInstance>.<valueAttribute>eft</valueAttribute> == allow))

[matchers]
<propertyKey>m</propertyKey> = <objectInstance>r</objectInstance>.<valueAttribute>sub</valueAttribute> == <objectInstance>p</objectInstance>.<valueAttribute>sub</valueAttribute> && <objectInstance>r</objectInstance>.<valueAttribute>obj</valueAttribute> == <objectInstance>p</objectInstance>.<valueAttribute>obj</valueAttribute> && <objectInstance>r</objectInstance>.<valueAttribute>act</valueAttribute> == <objectInstance>p</objectInstance>.<valueAttribute>act</valueAttribute> || <objectInstance>r</objectInstance>.<valueAttribute>sub</valueAttribute> == <stringValue>"root"</stringValue>
        """.trimMargin()

    override fun getAdditionalHighlightingTagToDescriptorMap() = ADDITIONAL_DESCRIPTORS

    override fun getAttributeDescriptors() = DESCRIPTORS

    override fun getColorDescriptors(): Array<out ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY!!

    override fun getDisplayName() = "Casbin"
}
package io.github.will7200.plugins.casbincsv.language.lexer

import com.intellij.util.xmlb.Converter
import java.util.*
import java.util.regex.Pattern

class CasbinCSVEscapeCharacter private constructor(
    val character: String,
    val display: String,
    val name: String
) {
    private val myPattern: Pattern = Pattern.compile(Pattern.quote(character + "\""))

    class CasbinCSVEscapeCharacterConverter : Converter<CasbinCSVEscapeCharacter?>() {
        override fun fromString(value: String): CasbinCSVEscapeCharacter? {
            return create(
                value
            )
        }

        override fun toString(value: CasbinCSVEscapeCharacter): String? {
            return value.character
        }
    }

    constructor(myCharacter: String) : this(
        myCharacter,
        "$CUSTOM_DISPLAY ($myCharacter)",
        CUSTOM_NAME
    ) {
    }

    fun isEscapedQuote(text: String?): Boolean {
        return myPattern.matcher(text).matches()
    }

    val isCustom: Boolean
        get() = CUSTOM_NAME == name

    override fun hashCode(): Int {
        return Objects.hash(character, isCustom)
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is CasbinCSVEscapeCharacter) {
            return false
        }
        val otherObj = other
        return otherObj.character == character && otherObj.isCustom == isCustom
    }

    override fun toString(): String {
        return display
    }

    companion object {
        private const val CUSTOM_NAME = "CUSTOM"
        private const val CUSTOM_DISPLAY = "Custom"

        @kotlin.jvm.JvmField
        var QUOTE = CasbinCSVEscapeCharacter(
            "\"",
            "Double Quote (\")",
            "QUOTE"
        )

        @kotlin.jvm.JvmField
        var BACKSLASH = CasbinCSVEscapeCharacter(
            "\\",
            "Backslash (\\)",
            "BACKSLASH"
        )

        fun values(): Array<CasbinCSVEscapeCharacter> {
            return arrayOf(
                QUOTE,
                BACKSLASH
            )
        }

        fun getDefaultEscapeCharacter(character: String?): CasbinCSVEscapeCharacter? {
            if (character != null) {
                when (character) {
                    "QUOTE", "\"" -> return QUOTE
                    "BACKSLASH", "\\" -> return BACKSLASH
                    else -> {
                    }
                }
            }
            return null
        }

        fun create(character: String?): CasbinCSVEscapeCharacter? {
            if (character == null) {
                return null
            }
            val defaultEscapeCharacter =
                getDefaultEscapeCharacter(
                    character
                )
            return defaultEscapeCharacter ?: CasbinCSVEscapeCharacter(
                character
            )
        }
    }

}
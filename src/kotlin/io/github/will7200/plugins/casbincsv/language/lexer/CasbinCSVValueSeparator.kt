package io.github.will7200.plugins.casbincsv.language.lexer

import com.intellij.util.xmlb.Converter
import java.util.*
import java.util.regex.Pattern

class CasbinCSVValueSeparator private constructor(
    val character: String,
    private val display: String,
    val name: String
) {
    private val myPattern: Pattern = Pattern.compile(Pattern.quote(character))

    class CasbinCSVValueSeparatorConverter : Converter<CasbinCSVValueSeparator?>() {
        override fun fromString(value: String): CasbinCSVValueSeparator? {
            return create(
                value
            )
        }

        override fun toString(value: CasbinCSVValueSeparator): String? {
            return value.character
        }
    }

    constructor(myCharacter: String) : this(
        myCharacter,
        "$CUSTOM_DISPLAY ($myCharacter)",
        CUSTOM_NAME
    ) {
    }

    fun isValueSeparator(text: String?): Boolean {
        return myPattern.matcher(text!!).matches()
    }

    val isCustom: Boolean
        get() = CUSTOM_NAME == name

    override fun hashCode(): Int {
        return Objects.hash(character, isCustom)
    }

    override fun equals(obj: Any?): Boolean {
        if (obj == null || obj !is CasbinCSVValueSeparator) {
            return false
        }
        val otherObj = obj
        return otherObj.character == character && otherObj.isCustom == isCustom
    }

    override fun toString(): String {
        return display
    }

    companion object {
        private const val CUSTOM_NAME = "CUSTOM"
        private const val CUSTOM_DISPLAY = "Custom"
        val COMMA = CasbinCSVValueSeparator(
            ",",
            "Comma (,)",
            "COMMA"
        )
        val SEMICOLON = CasbinCSVValueSeparator(
            ";",
            "Semicolon (;)",
            "SEMICOLON"
        )
        val PIPE = CasbinCSVValueSeparator(
            "|",
            "Pipe (|)",
            "PIPE"
        )
        val TAB = CasbinCSVValueSeparator(
            "\t",
            "Tab (↹)",
            "TAB"
        )
        val COLON = CasbinCSVValueSeparator(
            ":",
            "Colon (:)",
            "COLON"
        )

        fun getDefaultValueSeparator(character: String?): CasbinCSVValueSeparator? {
            if (character != null) {
                when (character) {
                    "COMMA", "," -> return COMMA
                    "SEMICOLON", ";" -> return SEMICOLON
                    "PIPE", "|" -> return PIPE
                    "TAB", "\t" -> return TAB
                    "COLON", ":" -> return COLON
                    else -> {
                    }
                }
            }
            return null
        }

        fun create(character: String?): CasbinCSVValueSeparator? {
            if (character == null) {
                return null
            }
            val defaultValueSeparator =
                getDefaultValueSeparator(
                    character
                )
            return defaultValueSeparator ?: CasbinCSVValueSeparator(
                character
            )
        }

        fun values(): Array<CasbinCSVValueSeparator> {
            return arrayOf(
                COMMA,
                SEMICOLON,
                PIPE,
                TAB,
                COLON
            )
        }
    }

}
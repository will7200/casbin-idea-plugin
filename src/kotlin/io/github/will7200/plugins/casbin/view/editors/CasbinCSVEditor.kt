package io.github.will7200.plugins.casbin.view.editors

import com.intellij.lang.Language
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.colors.EditorColors
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.project.Project
import com.intellij.ui.LanguageTextField
import io.github.will7200.plugins.casbin.CasbinExecutorService


open class CasbinCSVEditor(language: Language?, project: Project?, s: String) :
    LanguageTextField(language, project, s) {
    private val casbinService: CasbinExecutorService by lazy {
        project!!.service<CasbinExecutorService>()
    }

    init {
        this.addSettingsProvider { editor ->
            editor.settings.isWhitespacesShown = false
            editor.settings.isLineNumbersShown = true
            editor.settings.isVariableInplaceRenameEnabled = true
            editor.settings.isAutoCodeFoldingEnabled = true
            editor.settings.isBlinkCaret = true
            editor.settings.customSoftWrapIndent = 4
            editor.settings.isAdditionalPageAtBottom = true
            editor.settings.isIndentGuidesShown = true
            editor.settings.isFoldingOutlineShown = true
            editor.settings.isLineMarkerAreaShown = true
            editor.settings.isShowIntentionBulb = true
            editor.settings.setTabSize(4)
            editor.settings.isWheelFontChangeEnabled = true
            editor.settings.isRightMarginShown = true
            editor.settings.setUseTabCharacter(true)
            editor.settings.isSmartHome = true
            editor.settings.setRightMargin(10)
            editor.settings.isDndEnabled = true
            editor.settings.isCamelWords = true
            editor.settings.isAutoCodeFoldingEnabled = true
            editor.settings.setGutterIconsShown(true)
            val colorsScheme = editor.colorsScheme
            colorsScheme.setColor(EditorColors.CARET_ROW_COLOR, null)
            editor.colorsScheme = colorsScheme
        }
    }

    override fun createEditor(): EditorEx? {
        val editor = super.createEditor()
        editor.setHorizontalScrollbarVisible(true)
        editor.setVerticalScrollbarVisible(true)
        editor.component.preferredSize = null
        return editor
    }

    override fun isOneLineMode(): Boolean {
        return false
    }

}
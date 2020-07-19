package io.github.will7200.plugins.casbincsv.language

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.util.IconLoader
import javax.swing.Icon


val CasbinCSVFileIcon = IconLoader.getIcon("/icons/icon-14px.png")
val CasbinCSVFileTargetIcon: Icon = AllIcons.RunConfigurations.TestState.Run

object CasbinCSVFileType : LanguageFileType(CasbinCSVLanguage) {
    override fun getIcon() = CasbinCSVFileIcon
    override fun getName() = "CasbinCSV"
    override fun getDescription() = "Casbin CSV model definition"
    override fun getDefaultExtension() = "conf"
}
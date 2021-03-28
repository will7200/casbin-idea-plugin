package io.github.will7200.plugins.casbin.language

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.util.IconLoader
import javax.swing.Icon


val CasbinFileIcon = IconLoader.getIcon("/icons/casbin-icon-13x13.png", CasbinFileType::class.java)
val CasbinFileTargetIcon: Icon = AllIcons.RunConfigurations.TestState.Run

object CasbinFileType : LanguageFileType(CasbinLanguage) {
    override fun getIcon() = CasbinFileIcon
    override fun getName() = "Casbin"
    override fun getDescription() = "Casbin model definition"
    override fun getDefaultExtension() = "conf"
}
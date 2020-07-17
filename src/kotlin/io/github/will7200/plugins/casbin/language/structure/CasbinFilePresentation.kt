package io.github.will7200.plugins.casbin.language.structure

import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiFile
import io.github.will7200.plugins.casbin.language.CasbinFileIcon

open class CasbinFilePresentation(private val file: PsiFile) : ItemPresentation {

    private val presentableText = file.name

    override fun getPresentableText() = presentableText

    override fun getLocationString() = file.virtualFile.path

    override fun getIcon(b: Boolean) = CasbinFileIcon
}

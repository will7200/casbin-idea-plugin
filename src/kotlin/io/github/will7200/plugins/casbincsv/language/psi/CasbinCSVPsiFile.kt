package io.github.will7200.plugins.casbincsv.language.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import io.github.will7200.plugins.casbincsv.language.CasbinCSVFileConstants
import io.github.will7200.plugins.casbincsv.language.CasbinCSVFileType
import io.github.will7200.plugins.casbincsv.language.CasbinCSVLanguage

class CasbinCSVPsiFile(viewProvider: FileViewProvider) : PsiFileBase(
    viewProvider,
    CasbinCSVLanguage
) {
    override fun getFileType() = CasbinCSVFileType
    override fun toString() = CasbinCSVFileConstants.PSI_FILE_NAME
}
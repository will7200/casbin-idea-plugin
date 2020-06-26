package io.github.will7200.plugins.casbin.language.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import com.intellij.psi.util.PsiTreeUtil
import io.github.will7200.plugins.casbin.language.CasbinFileType
import io.github.will7200.plugins.casbin.language.CasbinLanguage
import io.github.will7200.plugins.casbin.language.CasbinFileConstants

class CasbinPsiFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider,
    CasbinLanguage
) {
    val sections: List<CasbinSection>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, CasbinSection::class.java)

    override fun getFileType() = CasbinFileType
    override fun toString() = CasbinFileConstants.PSI_FILE_NAME
}
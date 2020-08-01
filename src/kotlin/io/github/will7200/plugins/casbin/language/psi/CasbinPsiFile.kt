package io.github.will7200.plugins.casbin.language.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import com.intellij.psi.util.PsiTreeUtil
import io.github.will7200.plugins.casbin.language.CasbinFileConstants
import io.github.will7200.plugins.casbin.language.CasbinFileType
import io.github.will7200.plugins.casbin.language.CasbinLanguage

class CasbinPsiFile(viewProvider: FileViewProvider) : PsiFileBase(
    viewProvider,
    CasbinLanguage
) {
    override fun getFileType() = CasbinFileType
    override fun toString() = CasbinFileConstants.PSI_FILE_NAME
    val sections: List<CasbinSection>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, CasbinSection::class.java)

    fun sectionByName(name: String): CasbinSection? {
        return sections.first {
            it.header.sectionName?.text == name
        }
    }

    val properties: Collection<CasbinProperty>
        get() = PsiTreeUtil.findChildrenOfType(this, CasbinProperty::class.java)

    val objectReferences: Collection<CasbinObject>
        get() = PsiTreeUtil.findChildrenOfType(this, CasbinObject::class.java)

    fun attributesForProperty(parent: String): List<CasbinAttributeDefinition> {
        return attributes.filter {
            it.parent.parent.parent is CasbinProperty && (it.parent.parent.parent as CasbinProperty).name == parent
        }
    }

    val attributes: Collection<CasbinAttributeDefinition>
        get() = PsiTreeUtil.findChildrenOfAnyType(this, CasbinAttributeDefinition::class.java)
}
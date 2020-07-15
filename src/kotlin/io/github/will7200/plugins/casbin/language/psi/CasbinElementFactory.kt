package io.github.will7200.plugins.casbin.language.psi

import com.intellij.lang.ASTNode
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import io.github.will7200.plugins.casbin.language.CasbinFileType

object CasbinElementFactory {
    fun createFile(project: Project, text: String) =
        PsiFileFactory.getInstance(project).createFileFromText("Makefile", CasbinFileType, text) as CasbinPsiFile

    fun createSectionName(project: Project, section: String) =
        createFile(project, section).firstChild as ASTNode
}
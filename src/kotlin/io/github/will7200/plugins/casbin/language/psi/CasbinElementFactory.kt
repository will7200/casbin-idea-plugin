package io.github.will7200.plugins.casbin.language.psi

import com.intellij.lang.ASTNode
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.findDescendantOfType
import io.github.will7200.plugins.casbin.language.CasbinFileType

object CasbinElementFactory {
    private val log: Logger = Logger.getInstance(CasbinElementFactory::class.java)

    fun createFile(project: Project, text: String) =
        PsiFileFactory.getInstance(project).createFileFromText("policy.conf", CasbinFileType, text) as CasbinPsiFile

    fun createSectionName(project: Project, section: String) =
        createFile(project, section).firstChild as ASTNode

    fun createPropertyName(project: Project, propertyName: String): CasbinFlatKey =
        createFile(
            project, """
        [fake_section]
        ${propertyName} = sub, obj, act
    """.trimIndent()
        ).findDescendantOfType { true }!!

    fun createObjectName(project: Project, objectName: String): CasbinObject =
        createFile(
            project, """
        [fake_section]
        e = ${objectName}.property && o.property
    """.trimIndent()
        ).findDescendantOfType { true }!!

    fun createFunctionName(project: Project, functionName: String): CasbinFunctionName {
        return createFile(
            project, """
        [fake_section]
        e = ${functionName}(fakeargs)
    """.trimIndent()
        ).findDescendantOfType { true }!!
    }

    fun createAttributeName(project: Project, attributeName: String): CasbinAttribute {
        return createFile(
            project, """
        [fake_section]
        e = ${attributeName}, f1, f2
    """.trimIndent()
        ).findDescendantOfType { true }!!
    }
}
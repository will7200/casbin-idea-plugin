package io.github.will7200.plugins.casbin.language.psi

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiRecursiveElementWalkingVisitor
import io.github.will7200.plugins.casbin.language.CasbinFileType


/**
 * This should be com.intellij.psi.util.findDescendantOfType
 * but is currently missing from the EAP build
 *
 * Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 */
inline fun <reified T : PsiElement> PsiElement.findDescendantOfType(noinline predicate: (T) -> Boolean = { true }): T? {
    return findDescendantOfType({ true }, predicate)
}

/**
 * This should be com.intellij.psi.util.findDescendantOfType
 * but is currently missing from the EAP build
 *
 * Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 */
inline fun <reified T : PsiElement> PsiElement.findDescendantOfType(
    crossinline canGoInside: (PsiElement) -> Boolean,
    noinline predicate: (T) -> Boolean = { true }
): T? {
    var result: T? = null
    this.accept(object : PsiRecursiveElementWalkingVisitor() {
        override fun visitElement(element: PsiElement) {
            if (element is T && predicate(element)) {
                result = element
                stopWalking()
                return
            }

            if (canGoInside(element)) {
                super.visitElement(element)
            }
        }
    })
    return result
}

object CasbinElementFactory {
    private val log: Logger = Logger.getInstance(CasbinElementFactory::class.java)

    fun createFile(project: Project, text: String) =
        PsiFileFactory.getInstance(project).createFileFromText("policy.conf", CasbinFileType, text) as CasbinPsiFile

    fun createSectionName(project: Project, section: String): CasbinSection =
        createFile(project, "[${section}]").findDescendantOfType { true }!!

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

    fun createAttributeName(project: Project, attributeName: String): CasbinAttributeDefinition {
        return createFile(
            project, """
        [fake_section]
        e = ${attributeName}, f1, f2
    """.trimIndent()
        ).findDescendantOfType { true }!!
    }
}
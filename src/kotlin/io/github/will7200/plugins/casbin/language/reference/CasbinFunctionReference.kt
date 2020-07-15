package io.github.will7200.plugins.casbin.language.reference

import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import io.github.will7200.plugins.casbin.language.CasbinFileType
import io.github.will7200.plugins.casbin.language.psi.CasbinFunction
import io.github.will7200.plugins.casbin.language.psi.CasbinPsiFile
import io.github.will7200.plugins.casbin.language.psi.impl.CasbinFunctionMixin
import org.jetbrains.annotations.Nullable
import java.util.*


class CasbinFunctionReference(element: CasbinFunctionMixin) : PsiReferenceBase<CasbinFunctionMixin>(element),
    PsiPolyVariantReference {
    override fun resolve(): PsiElement? {
        val resolveResults = multiResolve(false)
        return if (resolveResults.size == 1) resolveResults[0].element else null
    }

    // Searches the entire project for Casbin language files with instances of the functions
    private fun findFunctions(
        project: Project?,
        key: String
    ): List<CasbinFunction> {
        val result: MutableList<CasbinFunction> = ArrayList()
        val virtualFiles =
            FileTypeIndex.getFiles(CasbinFileType, GlobalSearchScope.allScope(project!!))
        for (virtualFile in virtualFiles) {
            val casbinFile: CasbinPsiFile? = PsiManager.getInstance(project).findFile(virtualFile!!) as CasbinPsiFile?
            if (casbinFile != null) {
                val functions: Array<out @Nullable CasbinFunction?>? =
                    PsiTreeUtil.getChildrenOfType(casbinFile, CasbinFunction::class.java)
                if (functions != null) {
                    for (function in functions) {
                        if (function != null) {
                            if (key == function.name) {
                                result.add(function)
                            }
                        }
                    }
                }
            }
        }
        return result
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val properties: List<CasbinFunction>? = myElement.name?.let { findFunctions(myElement.project, it) }
        val results: MutableList<ResolveResult> =
            ArrayList()
        if (properties != null) {
            for (property in properties) {
                results.add(PsiElementResolveResult(property))
            }
        }
        return results.toTypedArray()
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return multiResolve(false).any { it.element == element }
    }

    override fun handleElementRename(newElementName: String): PsiElement {
        myElement.setName(newElementName)
        return myElement
    }
}
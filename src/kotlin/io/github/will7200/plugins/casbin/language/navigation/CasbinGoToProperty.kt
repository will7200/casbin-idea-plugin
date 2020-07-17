package io.github.will7200.plugins.casbin.language.navigation

import com.intellij.navigation.ChooseByNameContributor
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import io.github.will7200.plugins.casbin.language.CasbinFileType
import io.github.will7200.plugins.casbin.language.psi.CasbinProperty
import io.github.will7200.plugins.casbin.language.psi.CasbinPsiFile
import io.github.will7200.plugins.casbin.language.psi.impl.CasbinPropertyMixin
import java.util.*


class CasbinGoToProperty : ChooseByNameContributor {
    private val log: Logger = Logger.getInstance(CasbinGoToProperty::class.java)

    // Searches the entire project for Simple language files with instances of the Simple property with the given key
    private fun findProperties(
        project: Project?
    ): List<CasbinProperty>? {
        var result: List<CasbinProperty> = ArrayList()
        val virtualFiles =
            FileTypeIndex.getFiles(CasbinFileType, GlobalSearchScope.allScope(project!!))
        for (virtualFile in virtualFiles) {
            val simpleFile: CasbinPsiFile? = PsiManager.getInstance(project).findFile(virtualFile!!) as CasbinPsiFile?
            if (simpleFile != null) {
                val properties = simpleFile.properties
                result = listOf(result, properties).flatten()
            }
        }
        return result
    }

    override fun getNames(project: Project?, includeNonProjectItems: Boolean): Array<String> {
        val properties = findProperties(project) ?: return emptyArray()
        val names: MutableList<String> = ArrayList(properties.size)
        for (property in properties) {
            if (property.key != null && property.key!!.isNotEmpty()) {
                names.add(property.key!!)
            }
        }
        return names.toTypedArray()
    }

    override fun getItemsByName(
        name: String?,
        pattern: String?,
        project: Project?,
        includeNonProjectItems: Boolean
    ): Array<NavigationItem> {
        // TODO: include non project items
        val properties: List<CasbinProperty> = findProperties(project) ?: return emptyArray()
        return properties.asSequence()
            .map { it to it.key }
            .filter { (_, definedName) ->
                definedName == name || (pattern != null && (definedName ?: "").contains(pattern, ignoreCase = true))
            }
            .mapNotNull { (psi, _) -> (psi as CasbinPropertyMixin).createLabelNavigationItem(psi) }
            .toList()
            .toTypedArray()
    }
}
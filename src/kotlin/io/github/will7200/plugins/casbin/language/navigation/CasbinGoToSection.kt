package io.github.will7200.plugins.casbin.language.navigation

import com.intellij.navigation.ChooseByNameContributor
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import io.github.will7200.plugins.casbin.language.CasbinFileType
import io.github.will7200.plugins.casbin.language.psi.CasbinPsiFile
import io.github.will7200.plugins.casbin.language.psi.CasbinSection
import io.github.will7200.plugins.casbin.language.psi.impl.CasbinSectionMixin
import java.util.*


class CasbinGoToSection : ChooseByNameContributor {
    private val log: Logger = Logger.getInstance(CasbinGoToSection::class.java)

    // Searches the entire project for Simple language files with instances of the Simple property with the given key
    private fun findSections(
        project: Project?
    ): List<CasbinSection>? {
        var result: List<CasbinSection> = ArrayList()
        val virtualFiles =
            FileTypeIndex.getFiles(CasbinFileType, GlobalSearchScope.allScope(project!!))
        for (virtualFile in virtualFiles) {
            val simpleFile: CasbinPsiFile? = PsiManager.getInstance(project).findFile(virtualFile!!) as CasbinPsiFile?
            if (simpleFile != null) {
                val properties = simpleFile.sections
                result = listOf(result, properties).flatten()
            }
        }
        return result
    }

    override fun getNames(project: Project?, includeNonProjectItems: Boolean): Array<String> {
        val sections = findSections(project) ?: return emptyArray()
        val names: MutableList<String> = ArrayList(sections.size)
        for (property in sections) {
            if (property.name != null && property.name!!.isNotEmpty()) {
                names.add(property.name!!)
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
        val sections = findSections(project) ?: return emptyArray()
        return sections.asSequence()
            .map { it to it.name }
            .filter { (_, definedName) ->
                definedName == name || (pattern != null && (definedName ?: "").contains(pattern, ignoreCase = true))
            }
            .mapNotNull { (psi, _) -> (psi as CasbinSectionMixin).createLabelNavigationItem(psi) }
            .toList()
            .toTypedArray()
    }
}
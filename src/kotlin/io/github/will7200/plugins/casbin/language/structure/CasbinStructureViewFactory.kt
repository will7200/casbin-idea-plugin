package io.github.will7200.plugins.casbin.language.structure

import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

class CasbinStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(file: PsiFile) = object : TreeBasedStructureViewBuilder() {

        override fun createStructureViewModel(editor: Editor?): StructureViewModel {
            return CasbinStructureViewModel(file, editor)
        }
    }
}
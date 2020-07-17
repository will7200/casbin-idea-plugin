package io.github.will7200.plugins.casbin.language.structure

import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

class CasbinStructureViewModel(psiFile: PsiFile, editor: Editor?) :
    StructureViewModelBase(psiFile, editor, CasbinStructureViewElement(psiFile))

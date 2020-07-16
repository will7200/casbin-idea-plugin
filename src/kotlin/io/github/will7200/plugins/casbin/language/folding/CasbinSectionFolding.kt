package io.github.will7200.plugins.casbin.language.folding

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import io.github.will7200.plugins.casbin.language.psi.CasbinSection


class CasbinSectionFolding : FoldingBuilderEx() {
    override fun isCollapsedByDefault(node: ASTNode) = false
    override fun getPlaceholderText(node: ASTNode) = node.text.replace("]", "]\\n").substring(0, 30) + "..."
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        // Initialize the group of folding regions that will expand/collapse together.
        val literalExpressions: Collection<CasbinSection> = PsiTreeUtil.findChildrenOfType(
            root,
            CasbinSection::class.java
        )
        literalExpressions
            .map {
                descriptors.add(
                    FoldingDescriptor(
                        it.node,
                        TextRange(
                            it.textRange.startOffset,
                            it.textRange.endOffset
                        ),
                        FoldingGroup.newGroup(it.name)
                    )
                )
            }
        return descriptors.toTypedArray();
    }
}
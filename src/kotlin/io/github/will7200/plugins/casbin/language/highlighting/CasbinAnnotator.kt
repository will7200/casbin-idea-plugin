package io.github.will7200.plugins.casbin.language.highlighting

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import io.github.will7200.plugins.casbin.language.actions.CasbinCreateAttributeQuickFix
import io.github.will7200.plugins.casbin.language.psi.*

open class CasbinAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is CasbinObjectIdentifier -> annotate(element, holder)
            is CasbinAttributeDefinition -> annotate(element, holder)
            is CasbinAttribute -> annotate(element, holder)
            is CasbinFlatKey -> annotate(element, holder)
            is CasbinFunctionName -> annotate(element, holder)
            is CasbinStrings -> annotate(element, holder)
        }
    }


    private fun annotate(objectKey: CasbinFunctionName, holder: AnnotationHolder) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(objectKey)
            .textAttributes(CasbinHighlighterColors.FunctionName).create()
    }

    private fun annotate(objectKey: CasbinFlatKey, holder: AnnotationHolder) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(objectKey)
            .textAttributes(CasbinHighlighterColors.PropertyKey).create()
    }

    private fun annotate(obj: CasbinObjectIdentifier, holder: AnnotationHolder) {
        if (obj.parent.reference?.resolve() == null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Unresolved object").range(obj).apply {
                highlightType(ProblemHighlightType.LIKE_UNUSED_SYMBOL)
            }.create()
            return
        }
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(obj)
            .textAttributes(CasbinHighlighterColors.ObjectInstance).create()
    }

    private fun annotate(attribute: CasbinAttribute, holder: AnnotationHolder) {
        if (attribute.reference?.resolve() == null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Unresolved attribute for object").range(attribute).apply {
                highlightType(ProblemHighlightType.LIKE_UNUSED_SYMBOL)
            }.withFix(CasbinCreateAttributeQuickFix(attribute)).create()
            return
        }
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(attribute)
            .textAttributes(CasbinHighlighterColors.Attribute).create()
    }

    private fun annotate(attribute: CasbinAttributeDefinition, holder: AnnotationHolder) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(attribute)
            .textAttributes(CasbinHighlighterColors.Attribute).create()
    }

    private fun annotate(attribute: CasbinStrings, holder: AnnotationHolder) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(attribute)
            .textAttributes(CasbinHighlighterColors.StringValue).create()
    }

}
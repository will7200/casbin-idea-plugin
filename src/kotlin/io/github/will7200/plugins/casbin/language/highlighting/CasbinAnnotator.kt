package io.github.will7200.plugins.casbin.language.highlighting

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import io.github.will7200.plugins.casbin.language.psi.*

open class CasbinAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is CasbinObjectIdentifier -> annotate(element, holder)
            is CasbinAttributeDefinition -> annotate(element, holder)
            is CasbinAttribute -> annotate(element, holder)
            is CasbinFlatKey -> annotate(element, holder)
            is CasbinFunctionName -> annotate(element, holder)
            is CasbinStringValue -> annotate(element, holder)
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

    private fun annotate(objectKey: CasbinObjectIdentifier, holder: AnnotationHolder) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(objectKey)
            .textAttributes(CasbinHighlighterColors.ObjectInstance).create()
    }

    private fun annotate(attribute: CasbinAttribute, holder: AnnotationHolder) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(attribute)
            .textAttributes(CasbinHighlighterColors.Attribute).create()
    }

    private fun annotate(attribute: CasbinAttributeDefinition, holder: AnnotationHolder) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(attribute)
            .textAttributes(CasbinHighlighterColors.Attribute).create()
    }

    private fun annotate(attribute: CasbinStringValue, holder: AnnotationHolder) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(attribute)
            .textAttributes(CasbinHighlighterColors.StringValue).create()
    }

}
package io.github.will7200.plugins.casbincsv.language.psi

import com.intellij.psi.tree.IElementType
import io.github.will7200.plugins.casbincsv.language.CasbinCSVLanguage

open class CasbinCSVTokenType(debugName: String) : IElementType(
    debugName,
    CasbinCSVLanguage
)
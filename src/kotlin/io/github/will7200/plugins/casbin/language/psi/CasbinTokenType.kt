package io.github.will7200.plugins.casbin.language.psi

import com.intellij.psi.tree.IElementType
import io.github.will7200.plugins.casbin.language.CasbinLanguage

open class CasbinTokenType(debugName: String) : IElementType(debugName,
    CasbinLanguage
)
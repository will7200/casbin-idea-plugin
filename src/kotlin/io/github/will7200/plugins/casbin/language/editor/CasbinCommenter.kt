package io.github.will7200.plugins.casbin.language.editor

import com.intellij.lang.Commenter

open class CasbinCommenter : Commenter {

    override fun getLineCommentPrefix() = "#"

    override fun getBlockCommentPrefix(): String? = null

    override fun getBlockCommentSuffix(): String? = null

    override fun getCommentedBlockCommentPrefix(): String? = null

    override fun getCommentedBlockCommentSuffix(): String? = null
}
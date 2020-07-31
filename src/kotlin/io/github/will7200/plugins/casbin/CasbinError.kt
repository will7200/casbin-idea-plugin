package io.github.will7200.plugins.casbin

class CasbinError(override val message: String?, private val parent: Exception) : Exception() {
    val details: String? get() = parent.message
}
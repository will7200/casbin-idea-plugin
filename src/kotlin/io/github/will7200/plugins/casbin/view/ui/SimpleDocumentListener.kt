package io.github.will7200.plugins.casbin.view.ui

import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

@FunctionalInterface
interface SimpleDocumentListener : DocumentListener {
    fun update(e: DocumentEvent?)
    override fun insertUpdate(e: DocumentEvent?) {
        update(e)
    }

    override fun removeUpdate(e: DocumentEvent?) {
        update(e)
    }

    override fun changedUpdate(e: DocumentEvent?) {
        update(e)
    }
}
package io.github.will7200.plugins.casbin.view.ui

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class PersistedTextFieldWithBrowseButton : TextFieldWithBrowseButton() {
    private val log: Logger = Logger.getInstance(this::class.java)
    var project: Project? = null;
    var key: String? = null;
    private val pc: PropertiesComponent by lazy {
        project?.let {
            return@lazy PropertiesComponent.getInstance(it)
        }
        return@lazy PropertiesComponent.getInstance()
    }
    private val prefixKey = "io.github.will7200.plugins.casbin"
    private val internalKey: String by lazy {
        "${prefixKey}.${key!!}"
    }

    init {
        this.textField.document.addDocumentListener(object : SimpleDocumentListener {
            override fun update(e: DocumentEvent?) {
                key?.let {
                    pc.setValue(internalKey, text)
                }
            }
        })
    }

    fun load(pKey: String) {
        load(null, pKey)
    }

    fun load(pProject: Project?, pKey: String) {
        pProject?.let {
            this.project = it
        }
        this.key = pKey
        val text = pc.getValue(internalKey)
        super.setText(text)
    }

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
}
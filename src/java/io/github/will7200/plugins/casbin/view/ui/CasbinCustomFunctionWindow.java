package io.github.will7200.plugins.casbin.view.ui;

import com.intellij.lang.Language;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import io.github.will7200.plugins.casbin.view.editors.CasbinCSVEditor;

import javax.swing.*;

public class CasbinCustomFunctionWindow {
    private final Project myProject;
    private JList list1;
    private com.intellij.ui.EditorTextField editorPane1;
    private JPanel TopLevel;

    public CasbinCustomFunctionWindow(Project project, ToolWindow toolWindow) {
        myProject = project;
    }

    private void createUIComponents() {
        editorPane1 = new CasbinCSVEditor(Language.findLanguageByID("av"), myProject, "");
    }

    public JPanel getContent() {
        return TopLevel;
    }

    public com.intellij.ui.EditorTextField getEditor() {
        return editorPane1;
    }
}

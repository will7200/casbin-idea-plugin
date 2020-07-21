package io.github.will7200.plugins.casbin.view.ui;

import com.intellij.lang.Language;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.EditorTextField;
import io.github.will7200.plugins.casbin.view.editors.CasbinCSVEditor;

import javax.swing.*;

public class CasbinExecutorToolWindow {
    private JPanel TopLevel;
    private JPanel MainView;
    private JPanel Settings;
    private PersistedTextFieldWithBrowseButton policyFile;
    private PersistedTextFieldWithBrowseButton modelDefinitionFile;
    public EditorTextField requestEditorPane;
    private final Logger log = Logger.getInstance("CasbinExecutorToolWindow");

    public CasbinExecutorToolWindow(Project project, ToolWindow toolWindow) {
        policyFile.addBrowseFolderListener(new TextBrowseFolderListener(new FileChooserDescriptor(true, false, false, false, false, false)));
        modelDefinitionFile.addBrowseFolderListener(new TextBrowseFolderListener(new FileChooserDescriptor(true, false, false, false, false, false)));
//        policyFile.setText("C:\\Users\\wf08\\source\\casbin-idea-plugin\\test\\basic_policy.csv");
//        modelDefinitionFile.setText("C:\\Users\\wf08\\source\\casbin-idea-plugin\\test\\basic_modal.conf");
        policyFile.load(project, "policy_file");
        modelDefinitionFile.load(project, "model_def_file");
    }

    public JPanel getContent() {
        return TopLevel;
    }

    public String getPolicyFile() {
        return policyFile.getText();
    }

    public String getModelDefinitionFile() {
        return modelDefinitionFile.getText();
    }

    private void createUIComponents() {
        requestEditorPane = new CasbinCSVEditor(Language.findLanguageByID("CasbinCSV"), null, "");
    }
}

package io.github.will7200.plugins.casbin.view.ui;

import com.intellij.lang.Language;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.JBSplitter;
import io.github.will7200.plugins.casbin.view.editors.CasbinCSVEditor;

import javax.swing.*;

public class CasbinExecutorToolWindow {
    private final Project myProject;
    public final ToolWindow iToolWindow;
    private JPanel TopLevel;
    private JPanel MainView;
    private JPanel Settings;
    private PersistedTextFieldWithBrowseButton policyFile;
    private PersistedTextFieldWithBrowseButton modelDefinitionFile;
    public EditorTextField requestEditorPane;
    private JButton runTestButton;
    private JCheckBox runAsyncCheckBox;
    private JPanel Run;
    private JBSplitter LeftPane;
    private JBSplitter TopSplitter;
    private final Logger log = Logger.getInstance("CasbinExecutorToolWindow");

    public CasbinExecutorToolWindow(Project project, ToolWindow toolWindow) {
        myProject = project;
        iToolWindow = toolWindow;
        $$$setupUI$$$();
        policyFile.addBrowseFolderListener(new TextBrowseFolderListener(new FileChooserDescriptor(true, false, false, false, false, false)));
        modelDefinitionFile.addBrowseFolderListener(new TextBrowseFolderListener(new FileChooserDescriptor(true, false, false, false, false, false)));
        policyFile.load(project, "policy_file");
        modelDefinitionFile.load(project, "model_def_file");
    }

    private void $$$setupUI$$$() {
    }

    public JPanel getContent() {
        return TopLevel;
    }

    public PersistedTextFieldWithBrowseButton getPolicyFile() {
        return policyFile;
    }

    public PersistedTextFieldWithBrowseButton getModelDefinitionFile() {
        return modelDefinitionFile;
    }

    public String getPolicyFileText() {
        return policyFile.getText();
    }

    public String getModelDefinitionFileText() {
        return modelDefinitionFile.getText();
    }

    public JButton getRunTestButton() {
        return runTestButton;
    }

    public JCheckBox getRunAsyncCheckBox() {
        return runAsyncCheckBox;
    }

    private void createUIComponents() {
        requestEditorPane = new CasbinCSVEditor(Language.findLanguageByID("CasbinCSV"), myProject, "");
    }
}

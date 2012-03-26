/*
 * Copyright 2010-2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.jet.plugin.k2jsrun;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Pavel Talanov
 */
public final class K2JSRunConfigurationEditor extends SettingsEditor<K2JSRunConfiguration> {

    private JPanel mainPanel;
    private TextFieldWithBrowseButton chooseFile;
    @NotNull
    private final Project project;

    public K2JSRunConfigurationEditor(@NotNull Project project) {
        this.project = project;
    }

    @Override
    protected void resetEditorFrom(K2JSRunConfiguration configuration) {
        chooseFile.setText(configuration.settings().getFilePath());
    }

    @Override
    protected void applyEditorTo(@NotNull K2JSRunConfiguration configuration) throws ConfigurationException {
        configuration.settings().setFilePath(FileUtil.toSystemIndependentName(chooseFile.getText()));
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        FileChooserDescriptor fileChooserDescriptor = FileChooserDescriptorFactory.createSingleFileDescriptor(StdFileTypes.HTML);
        fileChooserDescriptor.setRoots(ProjectRootManager.getInstance(project).getContentRootUrls());
        chooseFile.addBrowseFolderListener("Choose file", "Yeah!", project, fileChooserDescriptor);
        return mainPanel;
    }

    @Override
    protected void disposeEditor() {
        // do nothing
    }
}

/*
    Copyright 2015-2018 Will Winder

    This file is part of Universal Gcode Sender (UGS).

    UGS is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    UGS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with UGS.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.willwinder.universalgcodesender.uielements.actions;

import com.willwinder.universalgcodesender.model.BackendAPI;
import com.willwinder.universalgcodesender.utils.Settings;
import com.willwinder.universalgcodesender.utils.SettingsFactory;
import com.willwinder.universalgcodesender.utils.SwingHelpers;
import com.willwinder.universalgcodesender.utils.ThreadHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 *
 * @author wwinder
 */
public class OpenGcodeFileAction extends AbstractAction {
    private BackendAPI backend;

    public OpenGcodeFileAction(BackendAPI backend) {
        this.backend = backend;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        openGcodeFileDialog();
    }
    
    public void openGcodeFile(File f) {
        ThreadHelper.invokeLater(() -> {
                try {
                    backend.setGcodeFile(f);
                    Settings settings = backend.getSettings();
                    settings.setLastOpenedFilename(f.getAbsolutePath());
                    SettingsFactory.saveSettings(settings);
                } catch (Exception e) {
                }
            });
    }

    public void openGcodeFileDialog() {
      String sourceDir = backend.getSettings().getLastOpenedFilename();
        SwingHelpers
                .openFile(sourceDir)
                .ifPresent(file -> openGcodeFile(file));
    }
}

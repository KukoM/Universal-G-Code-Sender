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
package com.willwinder.universalgcodesender.model;

import com.willwinder.universalgcodesender.gcode.GcodeState;
import com.willwinder.universalgcodesender.listeners.ControllerListener;
import com.willwinder.universalgcodesender.listeners.ControllerStateListener;
import com.willwinder.universalgcodesender.utils.Settings;
import com.willwinder.universalgcodesender.model.UGSEvent.ControlState;
import com.willwinder.universalgcodesender.pendantui.SystemStateBean;
import java.io.File;
import com.willwinder.universalgcodesender.listeners.UGSEventListener;

/**
 * Read only API used by front ends to interface with the model.
 *
 * @author wwinder
 */
public interface BackendAPIReadOnly {
    /**
     * Contains all common GUI events: state changes, probe events, settings changes and file changes.
     */
    void addUGSEventListener(UGSEventListener listener);

    /**
     * Listener for controller status events, these come at regular intervals. 
     */
    void addControllerStateListener(ControllerStateListener listener);

    /**
     * Details internal controller listener.
     */
    void addControllerListener(ControllerListener listener);
    
    // Config options
    File getGcodeFile();
    File getProcessedGcodeFile();

    // Controller status
    boolean isConnected();
    boolean isActive();
    boolean isSendingFile();
    boolean isIdle();
    boolean isPaused();
    boolean canPause();
    boolean canCancel();
    boolean canSend();
    ControlState getControlState();
    Position getWorkPosition();
    Position getMachinePosition();
    GcodeState getGcodeState();

    // Send status
    long getNumRows();
    long getNumSentRows();
    long getNumRemainingRows();

    long getSendDuration();
    long getSendRemainingDuration();
    String getPauseResumeText();

    // Bulk status getter.
    void updateSystemState(SystemStateBean systemStateBean);

    // Shouldn't be needed often.
    Settings getSettings();
}

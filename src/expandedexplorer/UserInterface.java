/*
 * Copyright 2014 Filip.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package expandedexplorer;

/**
 *
 * @author Filip
 */
public final class UserInterface {

    //<editor-fold defaultstate="collapsed" desc="INIT">
    private Logic logic;
    private GraphicUserInterface GUI;
    private SoundManager soundManager;
    private Status status;

    /**
     *
     * @param manager
     */
    public void assignSoundManager(SoundManager manager) {
	this.soundManager = manager;
    }

    /**
     *
     * @param logic
     */
    public void assignLogic(Logic logic) {
	this.logic = logic;
    }

    /**
     *
     * @param GUI
     */
    public void assignGUI(GraphicUserInterface GUI) {
	this.GUI = GUI;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="IINTERFACE">
    /**
     * updates listing
     *
     * @param listing with paths
     */
    public void updateListing(Listing listing) {
	GUI.changeListing(listing);
    }

    /**
     * updates status
     *
     * @param status
     */
    public void updateStatus(Status status) {
	GUI.changeStatus(status);
	this.status = status;
	switch (status) {
	    case incorrect:
		soundManager.playSound(soundManager.wrong);
		break;
	    case final_node:
		soundManager.playSound(soundManager.confirmation);
		break;
	}
    }

    /**
     * updates user input
     *
     * @param text
     */
    public void updateUserInput(String text) {
	GUI.changeUserInput(text);
    }

    /**
     * updates user input in selected mode
     *
     * @param text
     * @param mode
     */
    public void updateUserInput(String text, UserInputUpdateMode mode) {
	switch (mode) {
	    case backward:
		soundManager.playSound(soundManager.backward);
		break;
	    case forward:
		soundManager.playSound(soundManager.forward);
		break;
	    case reset:
		soundManager.playSound(soundManager.backward);
		break;
	}
	GUI.changeUserInput(text);
    }

    /**
     *
     * @return content of user input field
     */
    public String getUserInput() {
	return GUI.getUserInput();
    }

    /**
     *
     * @return acctual status
     */
    public Status getStatus() {
	return status;
    }
    //</editor-fold>

}

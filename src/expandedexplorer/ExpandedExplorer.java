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
public class ExpandedExplorer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	//creating logic
	Logic logic = new Logic();

	//creating file handler
	FileHandler fileHandler = new FileHandler();

	//creating ui
	UserInterface ui = new UserInterface();

	//creating gui
	GraphicUserInterface gui = new GraphicUserInterface();

	//creating gui responder
	GUIResponder responder = new GUIResponder();

	//creating sound manager
	SoundManager soundManager = new SoundManager();

	//passing arguments
	logic.assignUI(ui);
	logic.assignFileHandler(fileHandler);

	ui.assignLogic(logic);
	ui.assignGUI(gui);
	ui.assignSoundManager(soundManager);

	gui.assignResponder(responder);

	responder.assignLogic(logic);
	responder.assignUI(ui);

	//init
	gui.initComponents();
	logic.resetUserInput();
    }
}

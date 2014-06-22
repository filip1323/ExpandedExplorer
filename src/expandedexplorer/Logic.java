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
public class Logic {

    private UserInterface UI;
    private FileHandler fileHandler;

    //<editor-fold defaultstate="collapsed" desc="INIT">
    /**
     *
     * @param fileHandler
     */
    public void assignFileHandler(FileHandler fileHandler) {
	this.fileHandler = fileHandler;
    }

    /**
     *
     * @param gui
     */
    public void assignUI(UserInterface gui) {
	this.UI = gui;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="INTERFACE">
    /**
     * opens selected file if it is possible and closes program
     *
     * @param pathString
     */
    public void openPath(String pathString) {
	FileChecker path = new FileChecker(pathString);
	FileChecker rootPath = path.getRoot();
	if (path.pathExists()) {
	    fileHandler.openFile(path.getPath());
	} else {
	    fileHandler.openFile(rootPath.getPath());
	}
	System.exit(0);
	resetUserInput();
    }

    /**
     * changes user input
     *
     * @param input
     */
    public void updateUserInput(String input) {
	if (input.length() == 1) { //autofill drive path
	    UI.updateUserInput(input + ":/", UserInputUpdateMode.forward);
	    return;
	}
	if (input.length() < 3) { //drive path
	    return;
	}
	FileChecker path = new FileChecker(input);
	FileChecker rootPath = path.getRoot();

	String nodeName = path.getNodeName();
	if (rootPath.getPath().equals("")) { //main dir
	    UI.updateStatus(Status.incorrect);
	} else if (path.isCorrect()) { //seems right
	    if (path.isDir() && !path.hasNodes() || path.isFile()) { //has no more childs
		UI.updateStatus(Status.final_node);
		UI.updateListing(null);
	    } else if ((rootPath.pathExists() && rootPath.isDir()) || path.pathExists()) { //exists
		Listing listing = new Listing();
		listing.addPaths(fileHandler.getMatchingDirectoryListingString(path.getPath()));
		if (listing.getPaths().size() == 1) {
		    UI.updateUserInput(listing.getFirst(), UserInputUpdateMode.forward);
		    return;
		} else if (listing.getPaths().size() > 0) { //still some options
		    UI.updateStatus(Status.has_nodes);
		} else if (!path.pathExists()) { //no more options
		    UI.updateStatus(Status.incorrect);
		} else { //no more childs
		    UI.updateStatus(Status.final_node);
		}
		UI.updateListing(listing);
	    }
	} else {
	    UI.updateStatus(Status.incorrect);
	    UI.updateListing(null);
	}
    }

    /**
     * emptys user input and shows system drives listing
     */
    public void resetUserInput() {
	UI.updateUserInput("", UserInputUpdateMode.reset);
	Listing sysListing = new Listing();
	sysListing.addPaths(fileHandler.getSystemDrivesListingString());
	UI.updateListing(sysListing);
    }

    /**
     * clears last node of path [backward]
     */
    public void clearLastNode() {
	String pathString = UI.getUserInput();
	if (pathString.length() == 0) {
	    return;
	}
	FileChecker path = new FileChecker(pathString);
	if (path.isDrive() || pathString.length() <= 3) {
	    resetUserInput();
	    return;
	}
	FileChecker newPath = new FileChecker(pathString);
	do {

	    if (newPath.getPath().endsWith("/")) {
		newPath.setPath(newPath.getPath().substring(0, newPath.getPath().length() - 1));
	    }
	    newPath = newPath.getRoot();
	} while (fileHandler.getMatchingDirectoryListingString(newPath.getPath()).size() <= 1);
	UI.updateUserInput(newPath.getPath(), UserInputUpdateMode.backward);
    }
    //</editor-fold>

}

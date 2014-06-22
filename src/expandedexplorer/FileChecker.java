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

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Filip
 */
public class FileChecker {

    //<editor-fold defaultstate="collapsed" desc="INIT">
    private String pathToFile;
    private final FileHandler fileHandler;

    /**
     *
     * @param path absolute path to file
     */
    public FileChecker(String path) {
	this.pathToFile = path;
	this.fileHandler = new FileHandler();
    }

    /**
     *
     * @param path absolute path to file
     */
    public void setPath(String path) {
	pathToFile = path;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="INTERFACE">
    /**
     *
     * @return absolute path to file
     */
    public String getPath() {
	return pathToFile;
    }

    /**
     *
     * @return another instance of FileChecker, containing root of current path
     */
    public FileChecker getRoot() {
	if (pathToFile.matches("^[a-zA-Z]:(/|\\\\)")) {
	    return new FileChecker(pathToFile);
	}
	String parentPath = pathToFile.substring(0, pathToFile.length() - getNodeName().length());
	return new FileChecker(parentPath.replace("//", "/"));
    }

    /**
     *
     * @return last node name
     */
    public String getNodeName() {
	String pattern = "^[a-zA-Z]:(/|\\\\)((.)+(/|\\\\\\\\))*";
	String nodeName = pathToFile.replaceAll(pattern, "");
	return nodeName;
    }

    /**
     * checks if path is matching correct pattern
     *
     * @return
     */
    public boolean isCorrect() {
	boolean correctStatus = pathToFile.matches("^[a-zA-Z]:(/|\\\\)((.)+(/|\\\\)*)*");
	return correctStatus;
    }

    /**
     * checks if path leads to drive
     *
     * @return
     */
    public boolean isDrive() {
	boolean correctStatus = pathToFile.matches("^[a-zA-Z]:(/|\\\\)");
	return correctStatus;
    }

    /**
     * checks if path leads to directory
     *
     * @return
     */
    public boolean isDir() {
	File file = new File(pathToFile);
	boolean dirStatus = file.isDirectory();
	return dirStatus;
    }

    /**
     * checks if path leads to file
     *
     * @return
     */
    public boolean isFile() {
	File file = new File(pathToFile);
	if (file.isDirectory()) {
	    return false;
	}
	boolean fileStatus = file.isFile();
	return fileStatus;
    }

    /**
     * checks if path leads to existing file
     *
     * @return
     */
    public boolean pathExists() {
	File file = new File(pathToFile);
	boolean pathExists = file.exists();
	return pathExists;
    }

    /**
     * checks if path leads to directory with nodes
     *
     * @return
     */
    public boolean hasNodes() {
	ArrayList<String> nodes = fileHandler.getMatchingDirectoryListingString(pathToFile);
	return (nodes.size() > 0);
    }
//</editor-fold>

}

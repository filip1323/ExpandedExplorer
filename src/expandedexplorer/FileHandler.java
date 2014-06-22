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

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Filip
 */
public class FileHandler {

    //<editor-fold defaultstate="collapsed" desc="INTERFACE">
    /**
     * opens file using java.awt.Desktop class
     *
     * @param path absolute path of file
     *
     */
    public void openFile(String path) {
	try {
	    Desktop dt = Desktop.getDesktop();
	    dt.open(new File(path));
	} catch (IOException ex) {
	    ex.printStackTrace();
	};
    }

    /**
     *
     * @param directoryPath
     * @return list of underlying files matching to last node name
     */
    public ArrayList<String> getMatchingDirectoryListingString(String directoryPath) {
	FileChecker dirPathChecker = new FileChecker(directoryPath);
	String rootPath = dirPathChecker.getRoot().getPath();
	//String nodeName = getChildName(directoryPath);
	ArrayList<String> allFiles;
	if (!rootPath.equals("")) {
	    allFiles = getDirectoryListingString(rootPath);
	} else {
	    return new ArrayList<String>();
	}
	ArrayList<String> matching = new ArrayList<>();
	for (String filePath : allFiles) {
	    if (filePath.toLowerCase().contains(directoryPath.toLowerCase())) {
		if (new FileChecker(filePath).isDir()) {
		    filePath += "/";
		}
		matching.add(filePath);
	    }
	}
	return matching;
    }

    /**
     *
     * @return list of system drives
     */
    public ArrayList<String> getSystemDrivesListingString() {
	ArrayList<String> drivesName = new ArrayList<>();
	File[] f = File.listRoots();
	for (int i = 0; i < f.length; i++) {
	    drivesName.add(f[i].toString().replace("\\", "/"));
	}
	return drivesName;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="UTILS">
    private ArrayList<File> getDirectoryListing(String directory) {
	File file = new File(directory);
	ArrayList<File> files = new ArrayList<File>(Arrays.asList(file.listFiles()));
	ArrayList<File> toAdd = new ArrayList<File>();
	files.addAll(toAdd);
	return files;
    }

    private ArrayList<String> getDirectoryListingString(String directoryName) {
	ArrayList<File> files = getDirectoryListing(directoryName);
	ArrayList<String> filepaths = new ArrayList<>();
	for (File f : files) {
	    filepaths.add(f.getAbsolutePath().replace("\\", "/"));
	}
	return filepaths;
    }
//</editor-fold>

}

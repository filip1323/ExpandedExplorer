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

import com.alee.laf.list.WebList;
import com.alee.laf.text.WebTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Filip
 */
public class GUIResponder implements ActionListener, DocumentListener, KeyListener, MouseListener {

//<editor-fold defaultstate="collapsed" desc="INIT">
    private Logic logic;
    private UserInterface UI;
    private WebList listing;

    /**
     *
     * @param logic
     */
    public void assignLogic(Logic logic) {
	this.logic = logic;
    }

    /**
     *
     * @param ui
     */
    public void assignUI(UserInterface ui) {
	UI = ui;
    }

    /**
     *
     * @param listing
     */
    public void assignListing(WebList listing) {
	this.listing = listing;
    }
//</editor-fold>

    @Override
    public void actionPerformed(ActionEvent e) {
	switch (e.getActionCommand()) {
	    case "Open":
		logic.openPath(UI.getUserInput());
		break;
	    case "Cancel":
		System.exit(0);
		break;
	    case "Backward":
		logic.clearLastNode();
		break;
	}
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
	logic.updateUserInput(((WebTextField) e.getDocument().getProperty("owner")).getText());
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
	logic.updateUserInput(((WebTextField) e.getDocument().getProperty("owner")).getText());
    }

    @Override
    public void keyReleased(KeyEvent e) {
	int index;
	switch (e.getKeyCode()) {
	    case 27: //esc
		if (UI.getUserInput().equals("")) {
		    System.exit(0);
		} else if (listing.getSelectedIndex() == -1) {
		    logic.resetUserInput();
		} else {
		    listing.clearSelection();
		}
		break;
	    case 37: //left
	    case 8: //backspace
		logic.clearLastNode();
		break;
	    case 40: //down
		index = listing.getSelectedIndex();
		listing.setSelectedIndex(++index);
		break;
	    case 38: //up
		index = listing.getSelectedIndex();
		if (index != -1) {
		    listing.setSelectedIndex(--index);
		}
		break;
	    case 39: //right
		if (listing.getSelectedIndex() != -1) {
		    String path = (String) listing.getSelectedValue();
		    UI.updateUserInput(path, UserInputUpdateMode.forward);
		}
		break;
	    case 10: //enter
		if (listing.getSelectedIndex() == -1) {
		    if (UI.getStatus() != Status.incorrect && !UI.getUserInput().equals("")) {
			logic.openPath(UI.getUserInput());
		    }
		} else {
		    String path = (String) listing.getSelectedValue();
		    logic.openPath(path);
		}
		break;
	}
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	UI.updateUserInput((String) listing.getSelectedValue(), UserInputUpdateMode.forward);
    }

//<editor-fold defaultstate="collapsed" desc="UNUSED LISTENERS">
    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }
//</editor-fold>

}

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

import com.alee.extended.image.WebImage;
import com.alee.extended.label.WebHotkeyLabel;
import com.alee.extended.label.WebLinkLabel;
import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.GroupingType;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.list.WebList;
import com.alee.laf.list.WebListModel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.separator.WebSeparator;
import com.alee.laf.text.WebTextField;
import com.alee.managers.tooltip.TooltipManager;
import com.alee.managers.tooltip.TooltipWay;
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Filip
 */
public class GraphicUserInterface extends WebFrame {

    //<editor-fold defaultstate="collapsed" desc="HEADER">
    private WebButton backwardButton;
    private ImageIcon backwardIcon;
    private WebButton cancelButton;
    private ImageIcon confirmImg;
    private GroupPanel content;
    private GroupPanel descGroup;
    private WebList fileList;
    private WebListModel fileListModel;
    private WebImage infoIcon;
    private WebTextField inputField;
    private ImageIcon moreImg;
    private WebButton okButton;
    private Status status;
    private ImageIcon wrongImg;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="INIT">
    private GUIResponder responder;

    /**
     *
     */
    public GraphicUserInterface() {
	//setting look and feel
	try {
	    UIManager.setLookAndFeel(new WebLookAndFeel());
	} catch (UnsupportedLookAndFeelException ex) {
	    ex.printStackTrace();
	}
	fileListModel = new WebListModel();
	fileList = new WebList(fileListModel);
    }

    /**
     *
     * @param responder
     */
    public void assignResponder(GUIResponder responder) {
	this.responder = responder;
	responder.assignListing(fileList);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GUI CREATOR">
    /**
     * creates visual representation of user interface and shows it
     */
    public void initComponents() {
//-----------------------------HEADER-----------------------------//

	//setting icon
	ImageIcon loupeIcon = new ImageIcon(expandedexplorer.Resources.getUrl("loupe-normal.png"));
	setIconImage(loupeIcon.getImage());

	//setting frame exitable
	setDefaultCloseOperation(EXIT_ON_CLOSE);

	//setting frame title
	setTitle("Expanded Explorer");

	//-----------------------------INFO ICONS-----------------------------//
	confirmImg = new ImageIcon(Resources.getUrl("confirm.png"));
	wrongImg = new ImageIcon(Resources.getUrl("wrong.png"));
	moreImg = new ImageIcon(Resources.getUrl("more.png"));
	infoIcon = new WebImage(moreImg);
	//-----------------------------BODY-----------------------------//
	//creating description
	createDescriptionGroup();

	//creating backward icon
	backwardIcon = new ImageIcon(Resources.getUrl("backward.png"));
	//creating backward button
	backwardButton = new WebButton(backwardIcon);
	//adding listener for backward button
	backwardButton.addActionListener(responder);
	backwardButton.setActionCommand("Backward");
	//setting backward button
	//backwardButton.setPreferredWidth(backwardIcon.getIconWidth());
	backwardButton.setMargin(0, 1, 0, 4);
	backwardButton.setEnabled(false);

	//creating input field
	inputField = new WebTextField("", 10);
	inputField.setInputPrompt("path...");
	inputField.setMargin(2, 0, 0, 4);
	inputField.setMinimumWidth(300);

	//creating  and setting icon for input field
	ImageIcon loupeIconSmall = new ImageIcon(Resources.getUrl("loupe-small.png"));
	inputField.setTrailingComponent(new WebImage(loupeIconSmall));
	//adding listener to input
	inputField.getDocument().addDocumentListener(responder);
	inputField.getDocument().putProperty("owner", inputField);
	inputField.addKeyListener(responder);

	GroupPanel inputGroup = new GroupPanel(GroupingType.fillLast, backwardButton, inputField
	);

	//creating file list
	fileList.addMouseListener(responder);

	//creating cancel button
	cancelButton = new WebButton("Cancel");
	//adding listener to cancel button
	cancelButton.addActionListener(responder);

	//creating ok button
	okButton = new WebButton("Open");
	//adding listener to ok button
	okButton.addActionListener(responder);

	//creating group for ok and cancel button
	GroupPanel buttonsGroup = new GroupPanel(GroupingType.fillLast, cancelButton, okButton);

	//creating email label
	WebLinkLabel emailLabel = new WebLinkLabel();
	emailLabel.setEmailLink("toddler2584@gmail.com");
	emailLabel.setFontSize(10);

	//creating author label
	WebLabel authorLabel = new WebLabel("Author: Filip Łukomski");
	authorLabel.setFontSize(10);

	//creating author group
	GroupPanel authorGroup = new GroupPanel(GroupingType.fillFirst, authorLabel, emailLabel);

	//-----------------------------CONTENT-----------------------------//
	//-----------------------------CONTENT-----------------------------//
	//-----------------------------CONTENT-----------------------------//
	//creating content panel with description, input field, ok and cancel
	content = new GroupPanel(5, false,
		new GroupPanel(GroupingType.fillFirst, descGroup, infoIcon),
		inputGroup,
		fileList,
		buttonsGroup,
		authorGroup
	);
	content.setMargin(5);
	content.addKeyListener(responder);

	//-----------------------------FINAL-----------------------------//
	//setting location on screen
	setLocation(100, 0);

	//adding content to frame
	add(content);

	//packing stuff
	pack();

	//making frame visible
	setVisible(true);

	//making frame non resizable
	setResizable(false);

    }

    private void createDescriptionGroup() {

	WebLabel info = new WebLabel("Help");
	info.setMargin(0, 5, 0, 10);
	info.setFontSize(14);
	WebHotkeyLabel left = new WebHotkeyLabel("Left");
	WebHotkeyLabel right = new WebHotkeyLabel("Right");
	WebHotkeyLabel up = new WebHotkeyLabel("Up");
	WebHotkeyLabel down = new WebHotkeyLabel("Down");

	WebHotkeyLabel enter = new WebHotkeyLabel("Enter");
	WebHotkeyLabel backspace = new WebHotkeyLabel("Backspace");
	WebHotkeyLabel esc = new WebHotkeyLabel("Esc");

	TooltipManager.setTooltip(left, "Go back", TooltipWay.down, 0);
	TooltipManager.setTooltip(right, "Explore selected component", TooltipWay.down, 0);
	TooltipManager.setTooltip(up, "Select another component", TooltipWay.down, 0);
	TooltipManager.setTooltip(down, "Select another component", TooltipWay.down, 0);

	TooltipManager.setTooltip(enter, "Open selected component", TooltipWay.down, 0);
	TooltipManager.setTooltip(backspace, "Go back", TooltipWay.down, 0);
	TooltipManager.setTooltip(esc, "Disable list / Get back to drives selection", TooltipWay.down, 0);

	descGroup = new GroupPanel(GroupingType.fillFirst, 3, info, enter, backspace, esc, new WebSeparator(), up, down, left, right);
	descGroup.setMargin(0, 0, 0, 10);
	for (Component c : descGroup.getComponents()) {
	    if (c instanceof WebHotkeyLabel) {
		((WebHotkeyLabel) c).setFontSize(10);
		((WebHotkeyLabel) c).setMargin(-1);
	    }
	}
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="INTERFACE">
    /**
     * changes listing
     *
     * @param listing
     */
    public void changeListing(Listing listing) {
	fileListModel.removeAllElements();
	if (listing != null) {
	    fileListModel.addElements(listing.getPaths());
	}
	pack();
    }

    /**
     * changes status of acctual path
     *
     * @param status
     */
    public void changeStatus(Status status) {
	switch (status) {
	    case incorrect:
		okButton.setEnabled(false);
		inputField.setEditable(true);
		//icon status
		infoIcon.setIcon(wrongImg);
		break;
	    case final_node:
		okButton.setEnabled(true);
		inputField.setEditable(false);
		//icon status
		infoIcon.setIcon(confirmImg);
		break;
	    case has_nodes:
		okButton.setEnabled(true);
		inputField.setEditable(true);
		//icon status
		infoIcon.setIcon(moreImg);
		break;
	}
    }

    /**
     * changes user input field
     *
     * @param text
     */
    public void changeUserInput(final String text) {
	if (text.equals("")) {
	    okButton.setEnabled(false);
	}
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		inputField.setText(text);
		backwardButton.setEnabled(text.length() != 0);
		inputField.requestFocus();
	    }
	});
    }

    /**
     *
     * @return content of user input field
     */
    public String getUserInput() {
	return inputField.getText();
    }
//</editor-fold>

}

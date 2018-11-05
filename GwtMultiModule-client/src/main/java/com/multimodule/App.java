package com.multimodule;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.multimodule.clients.client1.CustomPanel;
import com.multimodule.clients.client2.CustomButton;
import com.multimodule.clients.client3.SendButton;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
//        final Button sendButton = new Button("Send");
        final TextBox nameField = new TextBox();
        nameField.setText("GWT User");
        final Label errorLabel = new Label();

        // We can add style names to widgets
//        sendButton.addStyleName("sendButton");

        // Add the nameField and sendButton to the RootPanel
        // Use RootPanel.get() to get the entire body element
        RootPanel.get("nameFieldContainer").add(nameField);
        RootPanel.get("errorLabelContainer").add(errorLabel);

        // Focus the cursor on the name field when the app loads
        nameField.setFocus(true);
        nameField.selectAll();

        // Create the popup dialog box
        final DialogBox dialogBox = new DialogBox();
        dialogBox.setText("Remote Procedure Call");
        dialogBox.setAnimationEnabled(true);
        final Label textToServerLabel = new Label();
        final HTML serverResponseLabel = new HTML();




        final CustomButton closeButton = new CustomButton("Close", "closeButton");

        final SendButton sendButton = new SendButton(nameField, errorLabel, textToServerLabel, serverResponseLabel,dialogBox, closeButton);
        RootPanel.get("sendButtonContainer").add(sendButton);

        closeButton.addClickHandler(vent -> {
            dialogBox.hide();
            sendButton.setEnabled(true);
            sendButton.setFocus(true);
        });


        CustomPanel dialogVPanel = new CustomPanel(textToServerLabel, serverResponseLabel, closeButton);
        dialogBox.setWidget(dialogVPanel);

    }
}

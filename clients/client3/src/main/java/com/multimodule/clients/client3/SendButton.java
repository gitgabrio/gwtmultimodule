package com.multimodule.clients.client3;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.multimodule.apis.api3.FieldVerifier;
import com.multimodule.apis.api3.GreetingResponse;
import com.multimodule.apis.api3.GreetingService;
import com.multimodule.apis.api3.GreetingServiceAsync;

public class SendButton extends Button {

    /**
     * The message displayed to the user when the server cannot be reached or
     * returns an error.
     */
    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";

    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */
    private final GreetingServiceAsync greetingService = GWT
            .create(GreetingService.class);

    private final Label errorLabel;
    private final TextBox nameField;
    private final Label textToServerLabel;
    private final HTML serverResponseLabel;
    private final DialogBox dialogBox;
    private final Button closeButton;

    public SendButton(TextBox nameField,
                      Label errorLabel,
                      Label textToServerLabel,
                      HTML serverResponseLabel,
                      DialogBox dialogBox,
                      Button closeButton) {
        super("SendButton3");
        addStyleName("sendButton");
        getElement().setId("sendButton");
        addClickHandler(new MyHandler());
        this.errorLabel = errorLabel;
        this.nameField = nameField;
        this.textToServerLabel = textToServerLabel;
        this.serverResponseLabel = serverResponseLabel;
        this.dialogBox = dialogBox;
        this.closeButton = closeButton;
    }

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler {

        /**
         * Fired when the user clicks on the sendButton.
         */
        public void onClick(ClickEvent event) {
            sendNameToServer();
        }

        /**
         * Send the name from the nameField to the server and wait for a response.
         */
        private void sendNameToServer() {
            // First, we validate the input.
            errorLabel.setText("");
            String textToServer = nameField.getText();
            if (!FieldVerifier.isValidName(textToServer)) {
                errorLabel.setText("Please enter at least four characters");
                return;
            }

            // Then, we send the input to the server.
            setEnabled(false);
            textToServerLabel.setText(textToServer);
            serverResponseLabel.setText("");
            greetingService.greetServer(textToServer,
                                        new AsyncCallback<GreetingResponse>() {
                                            public void onFailure(Throwable caught) {
                                                // Show the RPC error message to the user
                                                dialogBox
                                                        .setText("Remote Procedure Call - Failure");
                                                serverResponseLabel
                                                        .addStyleName("serverResponseLabelError");
                                                serverResponseLabel.setHTML(SERVER_ERROR);
                                                dialogBox.center();
                                                closeButton.setFocus(true);
                                            }

                                            public void onSuccess(GreetingResponse result) {
                                                dialogBox.setText("Remote Procedure Call");
                                                serverResponseLabel
                                                        .removeStyleName("serverResponseLabelError");
                                                serverResponseLabel.setHTML(new SafeHtmlBuilder()
                                                                                    .appendEscaped(result.getGreeting())
                                                                                    .appendHtmlConstant("<br><br>I am running ")
                                                                                    .appendEscaped(result.getServerInfo())
                                                                                    .appendHtmlConstant(".<br><br>It looks like you are using:<br>")
                                                                                    .appendEscaped(result.getUserAgent())
                                                                                    .toSafeHtml());
                                                dialogBox.center();
                                                closeButton.setFocus(true);
                                            }
                                        });
        }
    }
}

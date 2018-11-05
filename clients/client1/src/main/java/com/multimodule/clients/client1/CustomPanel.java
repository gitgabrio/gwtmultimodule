package com.multimodule.clients.client1;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CustomPanel extends VerticalPanel {

    public CustomPanel() {
    }

    public CustomPanel(Label textToServerLabel, HTML serverResponseLabel, Button closeButton) {
        this();
        addStyleName("dialogVPanel");
        add(new HTML("<b>Iì CustomPanel from client1!!!!!  AGAAAAAAAAAAAAAAAIIIIIIIIIN</b>"));
        add(new HTML("<b>Sending name to the server:</b>"));
        add(textToServerLabel);
        add(new HTML("<br><b>Server replies:</b>"));
        add(serverResponseLabel);
        setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        add(closeButton);
    }
}

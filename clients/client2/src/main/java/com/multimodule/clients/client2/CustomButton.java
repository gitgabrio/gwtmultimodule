package com.multimodule.clients.client2;

import com.google.gwt.user.client.ui.Button;

public class CustomButton extends Button {

    public CustomButton(String label, String id) {
        setHTML("CustomButton-" + label);
        getElement().setId(id);
    }


}

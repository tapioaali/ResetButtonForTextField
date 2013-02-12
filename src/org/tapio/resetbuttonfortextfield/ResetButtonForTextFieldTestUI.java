package org.tapio.resetbuttonfortextfield;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
public class ResetButtonForTextFieldTestUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        TextField tf = new TextField();
        new ResetButtonForTextField().extend(tf);
        layout.addComponent(tf);
    }

}
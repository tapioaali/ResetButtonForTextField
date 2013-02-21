package org.vaadin.resetbuttonfortextfield;

import com.vaadin.server.AbstractClientConnector;
import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.TextField;

public class ResetButtonForTextField extends AbstractExtension {
    public static void extend(TextField field) {
        new ResetButtonForTextField().extend((AbstractClientConnector) field);
    }
}

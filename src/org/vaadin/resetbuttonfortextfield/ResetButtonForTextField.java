package org.vaadin.resetbuttonfortextfield;

import org.vaadin.resetbuttonfortextfield.widgetset.client.ResetButtonForTextFieldState;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.TextField;

public class ResetButtonForTextField extends AbstractExtension implements
        Property.ValueChangeListener {
    public void extend(TextField field) {
        super.extend(field);
        field.addValueChangeListener(this);
    }

    @Override
    public ResetButtonForTextFieldState getState() {
        return (ResetButtonForTextFieldState) super.getState();
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        getState().value = event.getProperty().getValue().toString();
    }
}

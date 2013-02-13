package org.vaadin.resetbuttonfortextfield.widgetset.client;

import org.vaadin.resetbuttonfortextfield.ResetButtonForTextField;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Label;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VTextField;
import com.vaadin.shared.ui.Connect;

@Connect(ResetButtonForTextField.class)
public class ResetButtonForTextFieldConnector extends
        AbstractExtensionConnector implements KeyUpHandler, ClickHandler,
        ValueChangeHandler<String>, AttachEvent.Handler,
        StateChangeEvent.StateChangeHandler {
    private static final long serialVersionUID = 975266906597151369L;

    private VTextField field;
    private Label resetButtonLabel;

    @Override
    protected void extend(ServerConnector target) {
        field = (VTextField) ((ComponentConnector) target).getWidget();
        field.addStyleName("resetbuttonfortextfield-field");

        resetButtonLabel = Label.wrap(DOM.createDiv());
        resetButtonLabel.addStyleName("resetbuttonfortextfield-button");

        field.addAttachHandler(this);
        field.addKeyUpHandler(this);
        field.addValueChangeHandler(this);

        resetButtonLabel.addClickHandler(this);
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        field.setValue(getState().value);
        setResetButtonVisibility();
    }

    private void setResetButtonVisibility() {
        if (field.getValue().isEmpty()) {
            resetButtonLabel.setVisible(false);
        } else {
            resetButtonLabel.setVisible(true);
        }
    }

    @Override
    public ResetButtonForTextFieldState getState() {
        return (ResetButtonForTextFieldState) super.getState();
    }

    @Override
    public void onAttachOrDetach(AttachEvent event) {
        if (event.isAttached()) {
            field.getElement().getParentElement()
                    .appendChild(resetButtonLabel.getElement());
            setResetButtonVisibility();
        }
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        setResetButtonVisibility();
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        String newText = field.getText();
        field.setValue(newText);
        setResetButtonVisibility();
    }

    @Override
    public void onClick(ClickEvent event) {
        field.setValue("");
        field.setText("");
        field.valueChange(true);
        setResetButtonVisibility();
    }

}

package org.vaadin.resetbuttonfortextfield.widgetset.client;

import org.vaadin.resetbuttonfortextfield.ResetButtonForTextField;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTML;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VTextField;
import com.vaadin.shared.ui.Connect;

@Connect(ResetButtonForTextField.class)
public class ResetButtonForTextFieldConnector extends
        AbstractExtensionConnector implements KeyUpHandler, ClickHandler,
        AttachEvent.Handler, StateChangeEvent.StateChangeHandler {

    private VTextField field;
    private HTML resetButtonDiv;

    @Override
    protected void extend(ServerConnector target) {
        field = (VTextField) ((ComponentConnector) target).getWidget();
        field.addStyleName(VTextField.CLASSNAME + "-withresetbutton");

        resetButtonDiv = HTML.wrap(DOM.createDiv());
        resetButtonDiv.addStyleName(VTextField.CLASSNAME + "-resetbutton");

        field.addAttachHandler(this);
        field.addKeyUpHandler(this);

        resetButtonDiv.addClickHandler(this);
    }

    @Override
    public void onAttachOrDetach(AttachEvent event) {
        if (event.isAttached()) {
            field.getElement().getParentElement()
                    .appendChild(resetButtonDiv.getElement());
            setResetButtonVisibility();
        }
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        setResetButtonVisibility();
    }

    @Override
    public ResetButtonForTextFieldState getState() {
        return (ResetButtonForTextFieldState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        field.setValue(getState().value);
        setResetButtonVisibility();
    }

    @Override
    public void onClick(ClickEvent event) {
        field.setValue("");
        field.valueChange(true);
        setResetButtonVisibility();
    }

    private void setResetButtonVisibility() {
        if (field.getValue().isEmpty()) {
            resetButtonDiv.setVisible(false);
        } else {
            resetButtonDiv.setVisible(true);
        }
    }
}

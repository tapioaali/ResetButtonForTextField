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

	private static final long serialVersionUID = -1784280042163496505L;

	public static final String CLASSNAME = "resetbuttonfortextfield";
	
    private VTextField textField;
    private HTML resetButton;

    @Override
    protected void extend(ServerConnector target) {
        textField = (VTextField) ((ComponentConnector) target).getWidget();
        textField.addStyleName(CLASSNAME + "-textfield");

        resetButton = HTML.wrap(DOM.createDiv());
        resetButton.addStyleName(CLASSNAME + "-resetbutton");

        textField.addAttachHandler(this);
        textField.addKeyUpHandler(this);

        resetButton.addClickHandler(this);
    }

    @Override
    public void onAttachOrDetach(AttachEvent event) {
        if (event.isAttached()) {
            textField.getElement().getParentElement()
                    .appendChild(resetButton.getElement());
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
        textField.setValue(getState().value);
        setResetButtonVisibility();
    }

    @Override
    public void onClick(ClickEvent event) {
        textField.setValue("");
        textField.valueChange(true);
        setResetButtonVisibility();
    }

    private void setResetButtonVisibility() {
        if (textField.getValue().isEmpty()) {
            resetButton.setVisible(false);
        } else {
            resetButton.setVisible(true);
        }
    }
}

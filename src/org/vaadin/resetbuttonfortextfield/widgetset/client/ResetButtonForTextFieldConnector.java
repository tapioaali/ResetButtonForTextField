package org.vaadin.resetbuttonfortextfield.widgetset.client;

import org.vaadin.resetbuttonfortextfield.ResetButtonForTextField;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.DOM;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VTextField;
import com.vaadin.shared.ui.Connect;

@Connect(ResetButtonForTextField.class)
public class ResetButtonForTextFieldConnector extends
        AbstractExtensionConnector implements KeyUpHandler,
        AttachEvent.Handler, StateChangeEvent.StateChangeHandler {
	private static final long serialVersionUID = -737765038361894693L;

	public static final String CLASSNAME = "resetbuttonfortextfield";

    private VTextField textField;
    private Element resetButtonElement;

    @Override
    protected void extend(ServerConnector target) {
        textField = (VTextField) ((ComponentConnector) target).getWidget();
        textField.addStyleName(CLASSNAME + "-textfield");

        resetButtonElement = DOM.createDiv();
        resetButtonElement.addClassName(CLASSNAME + "-resetbutton");

        textField.addAttachHandler(this);
        textField.addKeyUpHandler(this);

        target.addStateChangeHandler(new StateChangeEvent.StateChangeHandler() {
        private static final long serialVersionUID = -8439729365677484553L;        	
            @Override
            public void onStateChanged(StateChangeEvent stateChangeEvent) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {
                        updateResetButtonVisibility();
                    }
                });
            }
        });
    }

    public native void addResetButtonClickListener(Element el)
    /*-{
        var self = this; 
        el.onclick = $entry(function() { 
            self.@org.vaadin.resetbuttonfortextfield.widgetset.client.ResetButtonForTextFieldConnector::clearTextField()();
        }); 
    }-*/;

    public native void removeResetButtonClickListener(Element el)
    /*-{
        el.onclick = null;
    }-*/;

    @Override
    public void onAttachOrDetach(AttachEvent event) {
        if (event.isAttached()) {
            textField.getElement().getParentElement()
                    .insertAfter(resetButtonElement, textField.getElement());
            updateResetButtonVisibility();
            addResetButtonClickListener(resetButtonElement);
        } else {
            Element parentElement = resetButtonElement.getParentElement();
            if (parentElement != null) {
                parentElement.removeChild(resetButtonElement);
            }
            removeResetButtonClickListener(resetButtonElement);
        }
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        updateResetButtonVisibility();
    }

    private void updateResetButtonVisibility() {
        if (textField.getValue().isEmpty()) {
            resetButtonElement.getStyle().setDisplay(Display.NONE);
        } else {
            resetButtonElement.getStyle().clearDisplay();
        }
    }

    private void clearTextField() {
        textField.setValue("");
        textField.valueChange(true);
        updateResetButtonVisibility();
        textField.getElement().focus();
    }
}

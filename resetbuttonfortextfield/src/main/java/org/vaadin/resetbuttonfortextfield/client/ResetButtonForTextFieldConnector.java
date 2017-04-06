package org.vaadin.resetbuttonfortextfield.client;

import org.vaadin.resetbuttonfortextfield.ResetButtonForTextField;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.DOM;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VTextField;
import com.vaadin.client.ui.textfield.AbstractTextFieldConnector;
import com.vaadin.shared.ui.Connect;

@Connect(ResetButtonForTextField.class)
public class ResetButtonForTextFieldConnector extends AbstractExtensionConnector {

    private static final long serialVersionUID = -2256196181999395573L;

    public static final String CLASSNAME = "resetbuttonfortextfield";

    private AbstractTextFieldConnector textFieldConnector;
    private VTextField textField;
    private Element resetButtonElement;
    private final ResetButtonClickRpc resetButtonClickRpc = RpcProxy.create(
            ResetButtonClickRpc.class, this);

    @Override
    protected void extend(ServerConnector target) {
        textFieldConnector = (AbstractTextFieldConnector) target;
        textFieldConnector.addStateChangeHandler(stateChangeEvent ->
            Scheduler.get().scheduleDeferred(() -> updateResetButtonVisibility()));

        textField = (VTextField) textFieldConnector.getWidget();
        textField.addStyleName(CLASSNAME + "-textfield");

        resetButtonElement = DOM.createDiv();
        resetButtonElement.addClassName(CLASSNAME + "-resetbutton");

        textField.addAttachHandler(event -> handleAttach(event.isAttached()));
        textField.addKeyUpHandler(change -> updateResetButtonVisibility());
    }

    public native void addResetButtonClickListener(Element el)
    /*-{
        var self = this;
        el.onclick = $entry(function () {
            self.@org.vaadin.resetbuttonfortextfield.client.ResetButtonForTextFieldConnector::clearTextField()();
        });
    }-*/;

    public native void removeResetButtonClickListener(Element el)
    /*-{
        el.onclick = null;
    }-*/;

    private void handleAttach(boolean isAttached) {
        if (isAttached) {
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

    private void updateResetButtonVisibility() {
        if (textField.getValue().isEmpty() || textField.isReadOnly()
                || !textField.isEnabled()
                || textField.getStyleName().contains("v-textfield-prompt")) {
            resetButtonElement.getStyle().setDisplay(Display.NONE);
        } else {
            resetButtonElement.getStyle().clearDisplay();
        }
    }

    private void clearTextField() {
        resetButtonClickRpc.resetButtonClick();
        textField.setValue("");
        textFieldConnector.sendValueChange();
        updateResetButtonVisibility();
        textField.getElement().focus();
    }
}

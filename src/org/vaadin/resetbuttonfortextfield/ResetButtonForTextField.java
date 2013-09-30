package org.vaadin.resetbuttonfortextfield;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.resetbuttonfortextfield.widgetset.client.ResetButtonClickRpc;

import com.vaadin.server.AbstractClientConnector;
import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.TextField;

public class ResetButtonForTextField extends AbstractExtension {
    private List<ResetButtonClickListener> listeners = new ArrayList<ResetButtonClickListener>();

    private ResetButtonClickRpc resetButtonClickRpc = new ResetButtonClickRpc() {
        @Override
        public void resetButtonClick() {
            for (ResetButtonClickListener listener : listeners) {
                listener.resetButtonClicked();
            }
        }
    };

    public static ResetButtonForTextField extend(TextField field) {
        ResetButtonForTextField resetButton = new ResetButtonForTextField();
        resetButton.extend((AbstractClientConnector) field);
        return resetButton;
    }

    public ResetButtonForTextField() {
        registerRpc(resetButtonClickRpc);
    }

    public void addResetButtonClickedListener(ResetButtonClickListener listener) {
        listeners.add(listener);
    }

    public void removeResetButtonClickListener(ResetButtonClickListener listener) {
        listeners.remove(listener);
    }
}

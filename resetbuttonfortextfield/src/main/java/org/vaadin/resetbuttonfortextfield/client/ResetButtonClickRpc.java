package org.vaadin.resetbuttonfortextfield.client;

import com.vaadin.shared.communication.ServerRpc;

public interface ResetButtonClickRpc extends ServerRpc {
    public void resetButtonClick();
}

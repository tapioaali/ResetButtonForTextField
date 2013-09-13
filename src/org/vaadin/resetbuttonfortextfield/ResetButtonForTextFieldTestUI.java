package org.vaadin.resetbuttonfortextfield;

import java.util.Date;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class ResetButtonForTextFieldTestUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        final FormLayout layout = new FormLayout();
        layout.setMargin(true);
        setContent(layout);

        final TextField tf = new TextField("Type something, please");
        tf.setInputPrompt("Some text here");

        final ResetButtonForTextField resetButton = ResetButtonForTextField
                .extend(tf);

        layout.addComponent(tf);
        tf.setImmediate(true);
        tf.setWidth("300px");

        final Label textChangeEventListenerLabel = new Label();
        textChangeEventListenerLabel.setHeight("20px");
        textChangeEventListenerLabel.setCaption("I show text changes!");
        layout.addComponent(textChangeEventListenerLabel);

        final Label valueChangeListenerLabel = new Label();
        valueChangeListenerLabel.setCaption("I show value changes!");
        layout.addComponent(valueChangeListenerLabel);
        valueChangeListenerLabel.setHeight("20px");

        final Label resetButtonClickListenerLabel = new Label();
        resetButtonClickListenerLabel
                .setCaption("I notice reset button clicks!");
        layout.addComponent(resetButtonClickListenerLabel);
        resetButtonClickListenerLabel.setHeight("20px");

        tf.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                valueChangeListenerLabel.setValue(event.getProperty()
                        .getValue().toString());
            }
        });

        tf.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(TextChangeEvent event) {
                textChangeEventListenerLabel.setValue(event.getText());
            }
        });

        resetButton
                .addResetButtonClickedListener(new ResetButtonClickListener() {
                    private int clickCount = 0;

                    @Override
                    public void resetButtonClicked() {
                        clickCount++;
                        resetButtonClickListenerLabel
                                .setValue("Reset button clicked " + clickCount
                                        + " times");
                    }
                });

        Button b1 = new Button("Clear the value of the textfield",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        tf.setValue("");
                    }
                });
        Button b2 = new Button(
                "Set the value of the textfield to current date",
                new Button.ClickListener() {

                    @Override
                    public void buttonClick(ClickEvent event) {
                        tf.setValue(new Date().toString());
                    }
                });

        layout.addComponent(b1);
        layout.addComponent(b2);
    }

}
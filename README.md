Vaadin 7 add-on **ResetButtonForTextField** version 1.0.3. It adds a reset button to the TextField 
component allowing user to reset the field with one click.

The reset button is drawn using SVG images in supported browsers. With IE8 PNG versions of those are used.

Also an example project for my blog post [url_will_be_added_here].

Usage: add the JAR to your project, compile widgetset and then extend any TextField:

    TextField tf = new TextField();
    ResetButtonForTextField.extend(tf);

Grab it from Vaadin Directory: [http://vaadin.com/addon/resetbuttonfortextfield](http://vaadin.com/addon/resetbuttonfortextfield)

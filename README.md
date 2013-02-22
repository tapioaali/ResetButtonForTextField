Vaadin 7 add-on **ResetButtonForTextField** version 1.0.3. It adds a reset button to the TextField 
component that allows user to reset the field with one click.

The reset button is drawn using SVG images in supported browsers. For IE8 there PNG versions are used.


 Also an example project for my blog post.

Usage: add the JAR to your project, compile widgetset and extend any TextField:

    TextField tf = new TextField();
    ResetButtonForTextField.extend(tf);

Grab it from Vaadin Directory: [http://vaadin.com/addon/resetbuttonfortextfield](http://vaadin.com/addon/resetbuttonfortextfield)

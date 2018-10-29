[![Published on Vaadin  Directory](https://img.shields.io/badge/Vaadin%20Directory-published-00b4f0.svg)](https://vaadin.com/directory/component/resetbuttonfortextfield)
[![Stars on Vaadin Directory](https://img.shields.io/vaadin-directory/star/resetbuttonfortextfield.svg)](https://vaadin.com/directory/component/resetbuttonfortextfield)

# Reset Button for Text Field Extension #

Adds a reset button to a text field, allowing users to clear the value of the field with one click. Get it from Vaadin Directory: [https://vaadin.com/directory#!addon/resetbuttonfortextfield](https://vaadin.com/directory#!addon/resetbuttonfortextfield)

The reset button is drawn using SVG images in supported browsers. With IE8 PNG versions of those are used.

Also an example project for a blog post [Extending components in Vaadin 7](https://vaadin.com/blog/-/blogs/extending-components-in-vaadin-7).

## Usage ##
Either add Maven 

	<dependency>
	   <groupId>org.vaadin.addons</groupId>
	   <artifactId>resetbuttonfortextfield</artifactId>
	   <version>1.2.1</version>
	</dependency>

or Ivy dependency

	<dependency 
	   org="org.vaadin.addons" 
	   name="resetbuttonfortextfield" 
	   rev="1.2.1" />

or just add the JAR to your project, compile the widgetset and then extend any TextField:

    TextField tf = new TextField();
    ResetButtonForTextField.extend(tf);

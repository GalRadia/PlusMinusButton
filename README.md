# PlusMinusButton Library

`PlusMinusButton` is a custom Android widget that provides a simple interface for incrementing and decrementing a number within a specified range. This library is useful for applications that require user input for quantities, such as shopping carts, inventory management, or any numeric input.

## Features

- Increment and decrement buttons.
- Customizable range with minimum and maximum values.
- Customizable text size, text color, and background color.
- Listeners for button clicks.

## Installation

### XML

Add the `PlusMinusButton` to your layout XML:
```xml
<com.example.plusminusbuttonlibrary.PlusMinusButton
    android:id="@+id/plusMinusButton"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:initialNumber="0"
    app:finalNumber="10"
    app:textSize="16sp"
    app:textColor="@android:color/black"
    app:backGroundColor="@android:color/white"
    app:backgroundDrawable="@drawable/custom_background"/>

```
### Attributes

-   `initialNumber`: The starting number (default is 0).
-   `finalNumber`: The maximum number (default is `Integer.MAX_VALUE`).
-   `textSize`: The size of the text displayed (default is 13sp).
-   `textColor`: The color of the text.
-   `backGroundColor`: The background color of the widget.
-   `backgroundDrawable`: The drawable used for the background.
### Java
In your activity or fragment:
```java
PlusMinusButton plusMinusButton = findViewById(R.id.plusMinusButton);
plusMinusButton.setRange(0, 100); // Set minimum and maximum range

plusMinusButton.setOnPlusClickListener(v -> {
    // Handle plus button click
    int currentNumber = plusMinusButton.getCurrentNumber();
    // Your logic here
});

plusMinusButton.setOnMinusClickListener(v -> {
    // Handle minus button click
    int currentNumber = plusMinusButton.getCurrentNumber();
    // Your logic here
});
```
## Methods

-   `setCurrentNumber(int number)`: Sets the current number within the specified range.
-   `getCurrentNumber()`: Returns the current number.
-   `setOnPlusClickListener(OnClickListener listener)`: Sets the listener for the plus button.
-   `setOnMinusClickListener(OnClickListener listener)`: Sets the listener for the minus button.
-   `setRange(int min, int max)`: Sets the minimum and maximum range.

package dev.arctic.interactivemenuapi.interfaces;

/**
 * Interface representing a Toggle Element in the Interactive Menu API.
 * Toggle Elements can switch between two states: Pressed and Unpressed.
 */
public interface IToggleElement extends IElement {

    /**
     * Toggles the state of the element.
     */
    void toggle();

    /**
     * Gets whether the element is currently pressed.
     *
     * @return True if pressed, false otherwise.
     */
    boolean isPressed();

    /**
     * Sets whether the element is currently pressed.
     *
     * @param pressed True if pressed, false otherwise.
     */
    void setPressed(boolean pressed);
}

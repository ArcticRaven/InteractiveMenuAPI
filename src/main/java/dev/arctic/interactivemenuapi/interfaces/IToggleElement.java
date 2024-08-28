package dev.arctic.interactivemenuapi.interfaces;

import net.kyori.adventure.text.Component;

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

    /**
     * Stores the primary text for the toggle element.
     *
     * @param primaryText The primary text to store.
     */
    void storePrimaryText(Component primaryText);

    /**
     * Stores the secondary text for the toggle element.
     *
     * @param secondaryText The secondary text to store.
     */
    void storeSecondaryText(Component secondaryText);

    /**
     * Applies the primary text to the TextDisplayEntity.
     */
    void setPrimaryText();

    /**
     * Applies the secondary text to the TextDisplayEntity.
     */
    void setSecondaryText();

    /**
     * Applies the animation for the toggle element based on its current state.
     */
    void applyAnimation(int duration);
}

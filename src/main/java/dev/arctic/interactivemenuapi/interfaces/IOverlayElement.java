package dev.arctic.interactivemenuapi.interfaces;

import net.kyori.adventure.text.Component;

/**
 * Interface representing an Overlay Element in the Interactive Menu API.
 * Overlay Elements are layered in front of other elements and can either be timed or interactively removed.
 */
public interface IOverlayElement extends IElement {

    /**
     * Gets whether the overlay element should be interactively removed.
     *
     * @return True if interactively removed, false otherwise.
     */
    boolean isInteractToRemove();

    /**
     * Sets whether the overlay element should be interactively removed.
     *
     * @param interactToRemove True if interactively removed, false otherwise.
     */
    void setInteractToRemove(boolean interactToRemove);

    /**
     * Gets the display duration of the overlay element in ticks.
     *
     * @return The display duration in ticks.
     */
    long getDisplayDuration();

    /**
     * Sets the display duration of the overlay element in ticks.
     *
     * @param displayDuration The display duration in ticks.
     */
    void setDisplayDuration(long displayDuration);

    /**
     * Sets the text for the overlay element.
     *
     * @param text The text to display.
     */
    void setText(Component text);
}

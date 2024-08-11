package dev.arctic.interactivemenuapi.interfaces;

import net.kyori.adventure.text.Component;

/**
 * Interface representing a Text Element in the Interactive Menu API.
 * Text Elements have no interactions or animations.
 */
public interface ITextElement extends IElement {

    /**
     * Sets the text for the Text Element.
     *
     * @param text the text to set
     */
    void setText(Component text);
}
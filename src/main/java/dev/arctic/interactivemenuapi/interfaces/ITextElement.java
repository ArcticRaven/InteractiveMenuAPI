package dev.arctic.interactivemenuapi.interfaces;

import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.entity.TextDisplay;

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

    /**
     * Gets the current text of the Text Element.
     *
     * @return the current text
     */
    Component getText();

    /**
     * Sets the background color of the text.
     *
     * @param red   the red component (0-255)
     * @param green the green component (0-255)
     * @param blue  the blue component (0-255)
     * @param alpha the alpha component (0-255)
     */
    void setTextBackgroundColor(int red, int green, int blue, int alpha);

    /**
     * Sets the line width of the text.
     *
     * @param width the line width
     */
    void setTextLineWidth(int width);

    /**
     * Sets the opacity of the text.
     *
     * @param opacity the opacity (0-255)
     */
    void setTextOpacity(byte opacity);

    /**
     * Sets whether the text has a shadow.
     *
     * @param shadowed true if the text should have a shadow, false otherwise
     */
    void setTextShadowed(boolean shadowed);

    /**
     * Sets the alignment of the text.
     *
     * @param alignment the alignment to set
     */
    void setTextAlignment(TextDisplay.TextAlignment alignment);
}
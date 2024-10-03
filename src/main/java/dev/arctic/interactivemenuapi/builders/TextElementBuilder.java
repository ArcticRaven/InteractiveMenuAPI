package dev.arctic.interactivemenuapi.builders;

import dev.arctic.interactivemenuapi.interfaces.ITextElement;
import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Menu;
import dev.arctic.interactivemenuapi.objects.elements.TextElement;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.Vector;

public class TextElementBuilder {

    private Menu parentMenu;
    private Division parentDivision;
    private Vector offset;
    private boolean visible;
    private Component text;
    private Color backgroundColor;
    private int lineWidth;
    private byte opacity;
    private boolean shadowed;
    private TextDisplay.TextAlignment alignment;

    public TextElementBuilder setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
        return this;
    }

    public TextElementBuilder setParentDivision(Division parentDivision) {
        this.parentDivision = parentDivision;
        return this;
    }

    public TextElementBuilder setOffset(Vector offset) {
        this.offset = offset;
        return this;
    }

    public TextElementBuilder setText(Component text) {
        this.text = text;
        return this;
    }

    public TextElementBuilder setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public TextElementBuilder setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public TextElementBuilder setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
        return this;
    }

    public TextElementBuilder setOpacity(byte opacity) {
        this.opacity = opacity;
        return this;
    }

    public TextElementBuilder setShadowed(boolean shadowed) {
        this.shadowed = shadowed;
        return this;
    }

    public TextElementBuilder setAlignment(TextDisplay.TextAlignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public TextElement build() {
        TextElement textElement = new TextElement(parentMenu, parentDivision, offset, visible);
        if (text != null) {
            textElement.setText(text);
        }
        if (backgroundColor != null) {
            textElement.setTextBackgroundColor(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), backgroundColor.getAlpha());
        }
        textElement.setTextLineWidth(lineWidth);
        textElement.setTextOpacity(opacity);
        textElement.setTextShadowed(shadowed);
        if (alignment != null) {
            textElement.setTextAlignment(alignment);
        }
        return textElement;
    }
}

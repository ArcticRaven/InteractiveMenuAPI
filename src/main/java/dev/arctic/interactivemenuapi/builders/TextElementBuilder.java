package dev.arctic.interactivemenuapi.builders;

import dev.arctic.interactivemenuapi.interfaces.ITextElement;
import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Menu;
import dev.arctic.interactivemenuapi.objects.elements.TextElement;
import net.kyori.adventure.text.Component;
import org.bukkit.util.Vector;

public class TextElementBuilder {

    private Menu parentMenu;
    private Division parentDivision;
    private Vector offset;
    private Component text;

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

    public ITextElement build() {
        TextElement textElement = new TextElement(parentMenu, parentDivision, offset);
        if (text != null) {
            textElement.setText(text);
        }
        return (ITextElement) textElement;
    }
}

package dev.arctic.interactivemenuapi.builders;

import dev.arctic.interactivemenuapi.interfaces.ITextElement;
import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Menu;
import dev.arctic.interactivemenuapi.objects.elements.TextElement;
import org.bukkit.util.Vector;

public class TextElementBuilder {

    private Menu parentMenu;
    private Division parentDivision;
    private Vector offset;

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

    public ITextElement build() {
        return (ITextElement) new TextElement(parentMenu, parentDivision, offset);
    }
}

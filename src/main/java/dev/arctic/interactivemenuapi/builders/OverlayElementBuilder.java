package dev.arctic.interactivemenuapi.builders;

import dev.arctic.interactivemenuapi.interfaces.IOverlayElement;
import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Menu;
import dev.arctic.interactivemenuapi.objects.elements.OverlayElement;
import net.kyori.adventure.text.Component;
import org.bukkit.util.Vector;

public class OverlayElementBuilder {

    private Menu parentMenu;
    private Division parentDivision;
    private Vector offset;
    private boolean visible;
    private boolean interactToRemove = false;
    private long displayDuration = 0L;
    private Component text;

    public OverlayElementBuilder setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
        return this;
    }

    public OverlayElementBuilder setParentDivision(Division parentDivision) {
        this.parentDivision = parentDivision;
        return this;
    }

    public OverlayElementBuilder setOffset(Vector offset) {
        this.offset = offset.add(new Vector(0, 0, 0.1));
        return this;
    }

    public OverlayElementBuilder setInteractToRemove(boolean interactToRemove) {
        this.interactToRemove = interactToRemove;
        return this;
    }

    public OverlayElementBuilder setDisplayDuration(long displayDuration) {
        this.displayDuration = displayDuration;
        return this;
    }

    public OverlayElementBuilder setText(Component text){
        this.text = text;
        return this;
    }

    public OverlayElementBuilder setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public OverlayElement build() {
        return new OverlayElement(parentMenu, parentDivision, offset, visible, interactToRemove, displayDuration, text);
    }
}

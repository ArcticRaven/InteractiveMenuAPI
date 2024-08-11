package dev.arctic.interactivemenuapi.objects.elements;

import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Element;
import dev.arctic.interactivemenuapi.objects.Menu;
import org.bukkit.util.Vector;

public class TextElement extends Element {

    public TextElement(Menu parentMenu, Division parentDivision, Vector offset) {
        super(parentMenu, parentDivision, offset);
        this.interactionEntity.remove();
    }

    @Override
    public void onInteract() {
    }

    @Override
    public void applyAnimation() {
    }
}

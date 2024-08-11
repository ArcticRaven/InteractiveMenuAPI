package dev.arctic.interactivemenuapi.objects.elements;

import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Element;
import dev.arctic.interactivemenuapi.objects.Menu;
import net.kyori.adventure.text.Component;
import org.bukkit.util.Vector;

public class TextElement extends Element {

    public TextElement(Menu parentMenu, Division parentDivision, Vector offset) {
        super(parentMenu, parentDivision, offset);
        this.interactionEntity.remove();
    }

    public void setText(Component text){
        super.textDisplayEntity.text(text);
    }

    @Override
    public void onInteract() {
    }

    @Override
    public void applyAnimation() {
    }
}

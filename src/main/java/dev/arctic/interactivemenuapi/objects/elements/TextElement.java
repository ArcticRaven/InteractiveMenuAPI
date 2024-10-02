package dev.arctic.interactivemenuapi.objects.elements;

import dev.arctic.interactivemenuapi.interfaces.ITextElement;
import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Element;
import dev.arctic.interactivemenuapi.objects.Menu;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class TextElement extends Element implements ITextElement {

    public TextElement(Menu parentMenu, Division parentDivision, Vector offset, boolean visible) {
        super(parentMenu, parentDivision, offset, visible);
        this.interactionEntity.remove();
    }

    public void setText(Component text){
        super.textDisplayEntity.text(text);
    }

    @Override
    public void onInteract(Object input) {
    }

    @Override
    public Location getCurrentLocation() {
        return super.location;
    }

    @Override
    public void setCurrentLocation(Location currentLocation) {

    }
}

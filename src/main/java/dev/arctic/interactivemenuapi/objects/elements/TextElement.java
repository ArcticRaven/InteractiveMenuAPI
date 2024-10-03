package dev.arctic.interactivemenuapi.objects.elements;

import dev.arctic.interactivemenuapi.interfaces.ITextElement;
import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Element;
import dev.arctic.interactivemenuapi.objects.Menu;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.Vector;

public class TextElement extends Element implements ITextElement {

    public TextElement(Menu parentMenu, Division parentDivision, Vector offset, boolean visible) {
        super(parentMenu, parentDivision, offset, visible);
        this.interactionEntity.remove();
    }

    public void setText(Component text) {
        if (textDisplayEntity != null) {
            textDisplayEntity.text(text);
        }
    }

    public Component getText() {
        return textDisplayEntity != null ? textDisplayEntity.text() : Component.text("");
    }

    public void setTextBackgroundColor(int red, int green, int blue, int alpha) {
        if (textDisplayEntity != null) {
            textDisplayEntity.setBackgroundColor(Color.fromARGB(alpha, red, green, blue));
        }
    }

    public void setTextLineWidth(int width) {
        if (textDisplayEntity != null) {
            textDisplayEntity.setLineWidth(width);
        }
    }

    public void setTextOpacity(byte opacity) {
        if (textDisplayEntity != null) {
            textDisplayEntity.setTextOpacity(opacity);
        }
    }

    public void setTextShadowed(boolean shadowed) {
        if (textDisplayEntity != null) {
            textDisplayEntity.setShadowed(shadowed);
        }
    }

    public void setTextAlignment(TextDisplay.TextAlignment alignment) {
        if (textDisplayEntity != null) {
            textDisplayEntity.setAlignment(alignment);
        }
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
        this.location = currentLocation;
    }
}

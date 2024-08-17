package dev.arctic.interactivemenuapi.objects;

import dev.arctic.interactivemenuapi.interfaces.IElement;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.TextDisplay;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.function.Consumer;

@Getter
@Setter
public abstract class Element implements IElement {

    protected Menu parentMenu;
    protected Division parentDivision;
    protected Location location;
    protected Vector offset;
    protected Interaction interactionEntity;
    protected TextDisplay textDisplayEntity;
    private Consumer<Element> onInteract;

    public Element(Menu parentMenu, Division parentDivision, Vector offset) {
        this.parentMenu = parentMenu;
        this.parentDivision = parentDivision;
        this.offset = offset;
        this.location = parentDivision.getCurrentLocation().clone().add(offset);
        initializeEntities();
    }

    protected void initializeEntities() {
        this.interactionEntity = location.getWorld().spawn(location, Interaction.class, interaction -> {
            interaction.setPersistent(false);
            interaction.setMetadata("InteractiveMenu", new FixedMetadataValue(parentMenu.getPlugin(), this));
        });

        this.textDisplayEntity = location.getWorld().spawn(location, TextDisplay.class, textDisplay -> {
            textDisplay.setPersistent(false);
        });
    }

    public void updateLocation(Location divisionLocation) {
        Location newLocation = divisionLocation.clone().add(offset);
        interactionEntity.teleport(newLocation);
        textDisplayEntity.teleport(newLocation);

        this.location = newLocation;
    }

    public void setOpacity(int opacity) {
        if (textDisplayEntity != null) {
            if (opacity == 0) {
                textDisplayEntity.text(Component.text(" "));
            }
            textDisplayEntity.setBackgroundColor(Color.fromARGB(opacity, 0, 0, 0));
        }
    }

    public void cleanup() {
        if (interactionEntity != null && !interactionEntity.isDead()) {
            interactionEntity.remove();
        }
        if (textDisplayEntity != null && !textDisplayEntity.isDead()) {
            textDisplayEntity.remove();
        }
    }

    private Vector getAdjustedOffset(Location anchorLocation, Vector offset, float yaw) {
        yaw = (yaw % 360 + 360) % 360;

        double x = offset.getX();
        double y = offset.getY();
        double z = offset.getZ();

        if (yaw >= 315 || yaw < 45) {
            return new Vector(x, y, z);
        } else if (yaw >= 45 && yaw < 135) {
            return new Vector(-z, y, x);
        } else if (yaw >= 135 && yaw < 225) {
            return new Vector(-x, y, -z);
        } else {
            return new Vector(z, y, -x);
        }
    }

    public void setOnInteract(Consumer<Element> onInteract) {
        this.onInteract = onInteract;
    }

    public abstract void onInteract();
}

package dev.arctic.interactivemenuapi.objects;

import dev.arctic.interactivemenuapi.animation.Animation;
import dev.arctic.interactivemenuapi.animation.AnimationType;
import dev.arctic.interactivemenuapi.interfaces.IDivision;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Setter
public class Division implements IDivision {

    protected Menu ownerMenu;
    protected Location currentLocation;
    protected Vector offset;
    protected AnimationType animationType;
    protected double animationStepper;
    protected int duration;
    protected List<Element> elements = new CopyOnWriteArrayList<>();

    public Division(Menu ownerMenu, Location initialLocation, Vector offset, AnimationType animationType, double animationStepper, int duration) {
        this.ownerMenu = ownerMenu;
        this.currentLocation = initialLocation;
        this.offset = offset;
        this.animationType = animationType;
        this.animationStepper = animationStepper;
        this.duration = duration;
    }

    public void updateLocation(Location rootMenuLocation) {
        Location newLocation = rootMenuLocation.clone().add(getAdjustedOffset(offset, rootMenuLocation.getYaw()));

        if (animationType != AnimationType.NONE) {
            Animation animation = new Animation(animationType, animationStepper, duration);
            Animation.AnimationResult result = animation.apply();
            newLocation.add(result.vectorChange());
        }

        this.currentLocation = newLocation;

        for (Element element : elements) {
            element.updateLocation(newLocation);
        }
    }

    private Vector getAdjustedOffset(Vector offset, float yaw) {
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

    public void cleanup() {
        for (Element element : elements) {
            element.cleanup();
        }
        elements.clear();
    }

    public Menu getParentMenu() {
        return this.ownerMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.ownerMenu = parentMenu;
    }

    public void addElement(Element element) {
        this.elements.add(element);
    }
}

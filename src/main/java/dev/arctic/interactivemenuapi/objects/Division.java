package dev.arctic.interactivemenuapi.objects;

import dev.arctic.interactivemenuapi.animation.Animation;
import dev.arctic.interactivemenuapi.animation.AnimationType;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Setter
public class Division {

    protected Menu ownerMenu;
    protected Location currentLocation;
    protected Vector offset;
    protected AnimationType animationType;
    protected double animationStepper;
    protected List<Element> elements = new CopyOnWriteArrayList<>();

    public Division(Menu ownerMenu, Location initialLocation, Vector offset, AnimationType animationType, double animationStepper) {
        this.ownerMenu = ownerMenu;
        this.currentLocation = initialLocation;
        this.offset = offset;
        this.animationType = animationType;
        this.animationStepper = animationStepper;
    }

    public void updateLocation(Location rootMenuLocation) {
        Location newLocation = rootMenuLocation.clone().add(offset);

        if (animationType != AnimationType.NONE) {
            Animation animation = new Animation(animationType, animationStepper);
            Animation.AnimationResult result = animation.apply();
            newLocation.add(result.vectorChange());
            // Additional logic for handling opacity can be implemented here if needed
        }

        this.currentLocation = newLocation;

        for (Element element : elements) {
            element.updateLocation(newLocation);
        }
    }

    public void cleanup() {
        for (Element element : elements) {
            element.cleanup();
        }
        elements.clear();
    }
}

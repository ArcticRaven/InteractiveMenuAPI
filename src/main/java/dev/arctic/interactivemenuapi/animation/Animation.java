package dev.arctic.interactivemenuapi.animation;

import dev.arctic.interactivemenuapi.objects.Element;
import dev.arctic.interactivemenuapi.objects.Division;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/**
 * Represents an animation that can be applied to elements or divisions in the Interactive Menu API.
 */
public class Animation {
    private AnimationType type;
    private double stepper;
    private int duration;

    /**
     * Constructs an Animation with the specified type, stepper, and tick duration.
     *
     * @param type The type of animation.
     * @param stepper The step value for the animation.
     * @param tickDuration The duration of the animation in ticks.
     */
    public Animation(AnimationType type, double stepper, int tickDuration) {
        this.type = type;
        this.stepper = stepper;
        this.duration = tickDuration;
    }

    /**
     * Applies the animation and returns the result, which includes the vector change and opacity.
     *
     * @return The result of the animation, including the vector change and opacity.
     */
    public AnimationResult apply() {
        Vector vectorChange = new Vector(0, 0, 0);
        int opacity = 100; // Default opacity

        switch (type) {
            case LEFT -> vectorChange.setX(-stepper);
            case RIGHT -> vectorChange.setX(stepper);
            case UP -> vectorChange.setY(stepper);
            case DOWN -> vectorChange.setY(-stepper);
            case FORWARD -> vectorChange.setZ(stepper);
            case BACKWARD -> vectorChange.setZ(-stepper);
            case VISIBILITY -> opacity = (int) stepper;  // Assuming stepper represents opacity level
            case NONE -> {
                // No changes to vector or opacity
            }
        }

        return new AnimationResult(type, vectorChange, opacity);
    }

    /**
     * Updates the location of the input division relative to the root menu location.
     *
     * @param division The division whose location is to be updated.
     * @param rootMenuLocation The root location of the menu.
     */
    public void updateInputDivisionLocation(Division division, Location rootMenuLocation) {
        Plugin plugin = division.getParentMenu().getPlugin();
        new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (ticks >= duration) {
                    cancel();
                    return;
                }

                Location newLocation = rootMenuLocation.clone().add(division.getOffset());

                if (type != AnimationType.NONE) {
                    AnimationResult result = apply();
                    newLocation.add(result.vectorChange());
                }

                // Update the division's location
                division.updateLocation(newLocation);
                ticks++;
            }
        }.runTaskTimer(plugin, 0, 1);
    }

    /**
     * Updates the location of the specified element relative to its parent location.
     *
     * @param element The element whose location is to be updated.
     * @param parentLocation The location of the parent.
     */
    public void updateElementLocation(Element element, Location parentLocation) {
        Location newLocation = parentLocation.clone().add(element.getOffset());

        if (type != AnimationType.NONE) {
            AnimationResult result = apply();
            newLocation.add(result.vectorChange());
        }

        // Update the element's location
        element.setLocation(newLocation);
    }

    /**
     * Represents the result of an animation, including the type, vector change, and opacity.
     */
    public record AnimationResult(AnimationType type, Vector vectorChange, int opacity) {}
}

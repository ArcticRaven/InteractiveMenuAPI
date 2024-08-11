package dev.arctic.interactivemenuapi.animation;

import dev.arctic.interactivemenuapi.objects.Element;
import dev.arctic.interactivemenuapi.objects.Division;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Animation {
    private AnimationType type;
    private double stepper;
    private int duration;

    public Animation(AnimationType type, double stepper, int tickDuration) {
        this.type = type;
        this.stepper = stepper;
        this.duration = tickDuration;
    }

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
                    setDivisionOpacity(division, result.opacity());
                }

                division.setCurrentLocation(newLocation);

                for (Element element : division.getElements()) {
                    element.updateLocation(newLocation);
                }

                ticks++;
            }
        }.runTaskTimerAsynchronously(plugin, 0, 1);
    }

    private void setDivisionOpacity(Division division, int opacity) {
        for (Element element : division.getElements()) {
            element.setOpacity(opacity);
        }
    }

    public void updateElementLocation(Element element, Location parentLocation) {
        Plugin plugin = element.getParentMenu().getPlugin();
        new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (ticks >= duration) {
                    cancel();
                    return;
                }

                Location newLocation = parentLocation.clone().add(element.getOffset());

                if (type != AnimationType.NONE) {
                    AnimationResult result = apply();
                    newLocation.add(result.vectorChange());
                    setElementOpacity(element, result.opacity());
                }

                element.updateLocation(newLocation);

                ticks++;
            }
        }.runTaskTimerAsynchronously(plugin, 0, 1);
    }

    private void setElementOpacity(Element element, int opacity) {
        element.setOpacity(opacity);
    }

    public record AnimationResult(AnimationType type, Vector vectorChange, int opacity) {
    }
}

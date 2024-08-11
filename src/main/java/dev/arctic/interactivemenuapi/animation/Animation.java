package dev.arctic.interactivemenuapi.animation;

import dev.arctic.interactivemenuapi.objects.Element;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import dev.arctic.interactivemenuapi.objects.Division;

public class Animation {
    private AnimationType type;
    private double stepper;

    public Animation(AnimationType type, double stepper) {
        this.type = type;
        this.stepper = stepper;
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
    }

    private void setDivisionOpacity(Division division, int opacity) {
        // Implement the logic to set the opacity of the division
        // This might involve updating the opacity of all elements in the division
        for (Element element : division.getElements()) {
                element.setOpacity(opacity);
        }
    }

    public void updateElementLocation(Element element, Location parentLocation) {
        Location newLocation = parentLocation.clone().add(element.getOffset());

        if (type != AnimationType.NONE) {
            AnimationResult result = apply();
            newLocation.add(result.vectorChange());
            setElementOpacity(element, result.opacity());
        }

        element.updateLocation(newLocation);
    }

    private void setElementOpacity(Element element, int opacity) {
        element.setOpacity(opacity);
    }

    public record AnimationResult(AnimationType type, Vector vectorChange, int opacity) {
    }
}
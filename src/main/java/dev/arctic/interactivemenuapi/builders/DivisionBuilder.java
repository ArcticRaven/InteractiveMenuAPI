package dev.arctic.interactivemenuapi.builders;

import dev.arctic.interactivemenuapi.animation.AnimationType;
import dev.arctic.interactivemenuapi.interfaces.IDivision;
import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Menu;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class DivisionBuilder {

    private Menu parentMenu;
    private Location initialLocation;
    private Vector offset;
    private AnimationType animationType = AnimationType.NONE;
    private double animationStepper = 0.0;

    public DivisionBuilder setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
        return this;
    }

    public DivisionBuilder setInitialLocation(Location initialLocation) {
        this.initialLocation = initialLocation;
        return this;
    }

    public DivisionBuilder setOffset(Vector offset) {
        this.offset = offset;
        return this;
    }

    public DivisionBuilder setAnimationType(AnimationType animationType) {
        this.animationType = animationType;
        return this;
    }

    public DivisionBuilder setAnimationStepper(double animationStepper) {
        this.animationStepper = animationStepper;
        return this;
    }

    public IDivision build() {
        return (IDivision) new Division(parentMenu, initialLocation, offset, animationType, animationStepper);
    }
}

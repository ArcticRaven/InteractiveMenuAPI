package dev.arctic.interactivemenuapi.objects.elements;

import dev.arctic.interactivemenuapi.animation.AnimationType;
import dev.arctic.interactivemenuapi.interfaces.IToggleElement;
import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Element;
import dev.arctic.interactivemenuapi.objects.Menu;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class ToggleElement extends Element implements IToggleElement {

    private boolean isPressed;
    private AnimationType pressAnimationType;
    private double pressAnimationStepper;
    private Component primaryText;
    private Component secondaryText;
    private int duration;

    public ToggleElement(Menu parentMenu, Division parentDivision, Vector offset, boolean visible, AnimationType pressAnimationType, double pressAnimationStepper, Component primaryText, Component secondaryText, int duration) {
        super(parentMenu, parentDivision, offset, visible);
        this.isPressed = false;
        this.pressAnimationType = pressAnimationType;
        this.pressAnimationStepper = pressAnimationStepper;
        storePrimaryText(primaryText);
        storeSecondaryText(secondaryText);
        this.duration = duration;
        setPrimaryText();
    }

    @Override
    public void onInteract(Object input) {
        isPressed = !isPressed;
        if (pressAnimationType != AnimationType.NONE) {
            applyAnimation(duration);
        }
    }

    @Override
    public void setPrimaryText() {
        textDisplayEntity.text(primaryText);
    }

    @Override
    public void setSecondaryText() {
        textDisplayEntity.text(secondaryText);
    }

    @Override
    public void storePrimaryText(Component primaryText) {
        this.primaryText = primaryText;
    }

    @Override
    public void storeSecondaryText(Component secondaryText) {
        this.secondaryText = secondaryText;
    }

    @Override
    public void toggle() {
        isPressed = !isPressed;
    }

    @Override
    public boolean isPressed() {
        return isPressed;
    }

    @Override
    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    @Override
    public void applyAnimation(int duration) {
        if (pressAnimationType == AnimationType.PRESSED) {
            if (isPressed) {
                setSecondaryText();
            } else {
                setPrimaryText();
            }
        }
    }

    @Override
    public Location getCurrentLocation() {
        return null;
    }

    @Override
    public void setCurrentLocation(Location currentLocation) {
        location = currentLocation;
    }
}

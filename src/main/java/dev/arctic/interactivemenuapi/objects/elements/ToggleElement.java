package dev.arctic.interactivemenuapi.objects.elements;

import dev.arctic.interactivemenuapi.animation.Animation;
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

    public ToggleElement(Menu parentMenu, Division parentDivision, Vector offset, AnimationType pressAnimationType, double pressAnimationStepper, Component primaryText, Component secondaryText, int duration) {
        super(parentMenu, parentDivision, offset);
        this.isPressed = false;
        this.pressAnimationType = pressAnimationType;
        this.pressAnimationStepper = pressAnimationStepper;
        this.primaryText = primaryText;
        this.secondaryText = secondaryText;
        this.duration = duration;
    }

    @Override
    public void onInteract() {
        isPressed = !isPressed;
        if (pressAnimationType != AnimationType.NONE) {
            applyAnimation(duration);
        }
    }

    public void setPrimaryText(){
        textDisplayEntity.text(primaryText);
    }

    public void setSecondaryText(){
        textDisplayEntity.text(secondaryText);
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
    public void setPrimaryText(Component primaryText) {
        this.primaryText = primaryText;
    }

    @Override
    public void setSecondaryText(Component secondaryText) {
        this.secondaryText = secondaryText;
    }

    @Override
    public void applyAnimation(int duration) {
        if (pressAnimationType == AnimationType.PRESSED){
            if (isPressed){
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

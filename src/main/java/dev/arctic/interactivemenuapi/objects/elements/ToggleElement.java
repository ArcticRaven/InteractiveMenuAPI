package dev.arctic.interactivemenuapi.objects.elements;

import dev.arctic.interactivemenuapi.animation.Animation;
import dev.arctic.interactivemenuapi.animation.AnimationType;
import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Element;
import dev.arctic.interactivemenuapi.objects.Menu;
import net.kyori.adventure.text.Component;
import org.bukkit.util.Vector;

public class ToggleElement extends Element {

    private boolean isPressed;
    private AnimationType pressAnimationType;
    private double pressAnimationStepper;
    private Component primaryText;
    private Component secondaryText;

    public ToggleElement(Menu parentMenu, Division parentDivision, Vector offset, AnimationType pressAnimationType, double pressAnimationStepper, Component primaryText, Component secondaryText) {
        super(parentMenu, parentDivision, offset);
        this.isPressed = false;
        this.pressAnimationType = pressAnimationType;
        this.pressAnimationStepper = pressAnimationStepper;
        this.primaryText = primaryText;
        this.secondaryText = secondaryText;
    }

    @Override
    public void onInteract() {
        isPressed = !isPressed;
        if (pressAnimationType != AnimationType.NONE) {
            applyAnimation();
        }
    }

    public void setPrimaryText(){
        textDisplayEntity.text(primaryText);
    }

    public void setSecondaryText(){
        textDisplayEntity.text(secondaryText);
    }

    @Override
    public void applyAnimation() {
        if (pressAnimationType != AnimationType.NONE) {
            Animation animation = new Animation(pressAnimationType, pressAnimationStepper);
            Animation.AnimationResult result = animation.apply();
            location.add(result.vectorChange());
            interactionEntity.teleport(location);
            textDisplayEntity.teleport(location);
        }

        if (pressAnimationType == AnimationType.PRESSED){
            if (isPressed){
                setSecondaryText();
            } else {
                setPrimaryText();
            }
        }
    }
}

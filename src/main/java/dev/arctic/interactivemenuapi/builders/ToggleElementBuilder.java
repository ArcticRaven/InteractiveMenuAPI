package dev.arctic.interactivemenuapi.builders;

import dev.arctic.interactivemenuapi.interfaces.*;
import dev.arctic.interactivemenuapi.objects.*;
import dev.arctic.interactivemenuapi.animation.AnimationType;
import dev.arctic.interactivemenuapi.objects.elements.ToggleElement;
import net.kyori.adventure.text.Component;
import org.bukkit.util.Vector;

public class ToggleElementBuilder {

    private Menu parentMenu;
    private Division parentDivision;
    private Vector offset;
    private AnimationType pressAnimationType = AnimationType.NONE;
    private double pressAnimationStepper = 0.0;
    private Component primaryText;
    private Component secondaryText;

    public ToggleElementBuilder setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
        return this;
    }

    public ToggleElementBuilder setParentDivision(Division parentDivision) {
        this.parentDivision = parentDivision;
        return this;
    }

    public ToggleElementBuilder setOffset(Vector offset) {
        this.offset = offset;
        return this;
    }

    public ToggleElementBuilder setPressAnimationType(AnimationType pressAnimationType) {
        this.pressAnimationType = pressAnimationType;
        return this;
    }

    public ToggleElementBuilder setPressAnimationStepper(double pressAnimationStepper) {
        this.pressAnimationStepper = pressAnimationStepper;
        return this;
    }

    public ToggleElementBuilder setPrimaryText(Component primaryText) {
        this.primaryText = primaryText;
        return this;
    }

    public ToggleElementBuilder setSecondaryText(Component secondaryText) {
        this.secondaryText = secondaryText;
        return this;
    }
    public ToggleElement build() {
        return new ToggleElement(parentMenu, parentDivision, offset, pressAnimationType, pressAnimationStepper, primaryText, secondaryText);
    }
}


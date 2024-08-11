package dev.arctic.interactivemenuapi.builders;

import dev.arctic.interactivemenuapi.interfaces.*;
import dev.arctic.interactivemenuapi.objects.*;
import dev.arctic.interactivemenuapi.animation.AnimationType;
import dev.arctic.interactivemenuapi.objects.elements.ToggleElement;
import org.bukkit.util.Vector;

public class ToggleElementBuilder {

    private Menu parentMenu;
    private Division parentDivision;
    private Vector offset;
    private AnimationType pressAnimationType = AnimationType.NONE;
    private double pressAnimationStepper = 0.0;

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

    public IToggleElement build() {
        return (IToggleElement) new ToggleElement(parentMenu, parentDivision, offset, pressAnimationType, pressAnimationStepper);
    }
}


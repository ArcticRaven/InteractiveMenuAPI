package dev.arctic.interactivemenuapi.builders;

import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Menu;
import dev.arctic.interactivemenuapi.animation.AnimationType;
import dev.arctic.interactivemenuapi.objects.elements.ToggleElement;
import net.kyori.adventure.text.Component;
import org.bukkit.util.Vector;

public class ToggleElementBuilder {

    private Menu parentMenu;
    private Division parentDivision;
    private Vector offset;
    private boolean visible;
    private AnimationType pressAnimationType = AnimationType.NONE;
    private double pressAnimationStepper = 0.0;
    private int duration;
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

    public ToggleElementBuilder setDuration(int duration) {
        this.duration = duration;
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

    public ToggleElementBuilder setVisibility(boolean visible) {
        this.visible = visible;
        return this;
    }

    public ToggleElement build() {
        ToggleElement toggleElement = new ToggleElement(parentMenu, parentDivision, offset, visible, pressAnimationType, pressAnimationStepper, primaryText, secondaryText, duration);
        if (primaryText != null) {
            toggleElement.setPrimaryTextContent();
        }
        if (secondaryText != null) {
            toggleElement.setSecondaryTextContent();
        }
        return toggleElement;
    }
}

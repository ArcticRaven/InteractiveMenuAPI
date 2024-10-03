package dev.arctic.interactivemenuapi.builders;

import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Menu;
import dev.arctic.interactivemenuapi.objects.elements.DisplayElement;
import org.bukkit.entity.Display;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class DisplayElementBuilder {

    private Menu parentMenu;
    private Division parentDivision;
    private Vector offset;
    private boolean visible;
    private ItemStack displayItem;
    private Display.Billboard transformation;
    private Display.Brightness brightness;
    private float shadowRadius;
    private float shadowStrength;
    private float viewRange;

    public DisplayElementBuilder setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
        return this;
    }

    public DisplayElementBuilder setParentDivision(Division parentDivision) {
        this.parentDivision = parentDivision;
        return this;
    }

    public DisplayElementBuilder setOffset(Vector offset) {
        this.offset = offset;
        return this;
    }

    public DisplayElementBuilder setDisplayItem(ItemStack displayItem) {
        this.displayItem = displayItem;
        return this;
    }

    public DisplayElementBuilder setVisibility(boolean visible) {
        this.visible = visible;
        return this;
    }

    public DisplayElementBuilder setTransformation(Display.Billboard transformation) {
        this.transformation = transformation;
        return this;
    }

    public DisplayElementBuilder setBrightness(Display.Brightness brightness) {
        this.brightness = brightness;
        return this;
    }

    public DisplayElementBuilder setShadow(float shadowRadius, float shadowStrength) {
        this.shadowRadius = shadowRadius;
        this.shadowStrength = shadowStrength;
        return this;
    }

    public DisplayElementBuilder setViewRange(float viewRange) {
        this.viewRange = viewRange;
        return this;
    }

    public DisplayElement build() {
        DisplayElement displayElement = new DisplayElement(parentMenu, parentDivision, offset, visible, displayItem);
        if (transformation != null) {
            displayElement.setTransformation(transformation);
        }
        if (brightness != null) {
            displayElement.setBrightness(brightness);
        }
        displayElement.setShadow(shadowRadius, shadowStrength);
        displayElement.setViewRange(viewRange);
        return displayElement;
    }
}

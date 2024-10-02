package dev.arctic.interactivemenuapi.builders;

import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Menu;
import dev.arctic.interactivemenuapi.objects.elements.DisplayElement;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class DisplayElementBuilder {

    private Menu parentMenu;
    private Division parentDivision;
    private Vector offset;
    private boolean visible;
    private ItemStack displayItem;

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

    public DisplayElement build() {

        return new DisplayElement(parentMenu, parentDivision, offset, visible, displayItem);
    }
}

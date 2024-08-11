package dev.arctic.interactivemenuapi.interfaces;

import org.bukkit.inventory.ItemStack;

/**
 * Interface representing a Display Element in the Interactive Menu API.
 * Display Elements show a single ItemStack.
 */
public interface IDisplayElement extends IElement {

    /**
     * Gets the item being displayed by this element.
     *
     * @return The displayed ItemStack.
     */
    ItemStack getDisplayItem();

    /**
     * Sets the item to be displayed by this element.
     *
     * @param displayItem The ItemStack to display.
     */
    void setDisplayItem(ItemStack displayItem);
}

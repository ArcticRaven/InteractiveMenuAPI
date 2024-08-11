package dev.arctic.interactivemenuapi.interfaces;

import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.ItemDisplay;

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

    /**
     * Gets the ItemDisplay entity associated with this element.
     *
     * @return The ItemDisplay entity.
     */
    ItemDisplay getDisplayEntity();

    /**
     * Sets the ItemDisplay entity for this element.
     *
     * @param displayEntity The ItemDisplay entity.
     */
    void setDisplayEntity(ItemDisplay displayEntity);

    /**
     * Changes the item being displayed by this element.
     *
     * @param newItem The new ItemStack to display.
     */
    void changeDisplayItem(ItemStack newItem);
}

package dev.arctic.interactivemenuapi.interfaces;

import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Display;

/**
 * Interface representing a Display Element in the Interactive Menu API.
 * Display Elements show a single ItemStack and provide customization options.
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

    /**
     * Sets the transformation matrix of the item display entity.
     *
     * @param transformation The transformation to be applied.
     */
    void setTransformation(Display.Billboard transformation);

    /**
     * Sets the brightness of the item display entity.
     *
     * @param brightness The brightness to be set.
     */
    void setBrightness(Display.Brightness brightness);

    /**
     * Sets the shadow radius and strength for the display entity.
     *
     * @param shadowRadius  The shadow radius.
     * @param shadowStrength The shadow strength.
     */
    void setShadow(float shadowRadius, float shadowStrength);

    /**
     * Sets the view range of the item display entity.
     *
     * @param viewRange The view range to be set.
     */
    void setViewRange(float viewRange);
}
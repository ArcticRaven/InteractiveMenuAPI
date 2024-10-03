package dev.arctic.interactivemenuapi.objects.elements;

import dev.arctic.interactivemenuapi.interfaces.IDisplayElement;
import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Element;
import dev.arctic.interactivemenuapi.objects.Menu;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Display;

/**
 * Represents a display element in the interactive menu.
 * This element is associated with an item display in the game world.
 */
public class DisplayElement extends Element implements IDisplayElement {

    private ItemStack displayItem;
    private ItemDisplay displayEntity;

    /**
     * Constructs a new DisplayElement with the specified parameters.
     *
     * @param parentMenu The parent menu to which this element belongs.
     * @param parentDivision The parent division to which this element belongs.
     * @param offset The offset of this element from its parent division's location.
     * @param displayItem The item stack to be displayed by this element.
     */
    public DisplayElement(Menu parentMenu, Division parentDivision, Vector offset, Boolean visible, ItemStack displayItem) {
        super(parentMenu, parentDivision, offset, visible);
        this.displayItem = displayItem;
        initializeDisplayItem();
    }

    /**
     * Initializes the display item in the game world.
     */
    private void initializeDisplayItem() {
        displayEntity = location.getWorld().spawn(location, ItemDisplay.class, item -> {
            item.setItemStack(displayItem);
            item.setPersistent(parentMenu.isPersistent());
        });
    }

    /**
     * Gets the item stack displayed by this element.
     *
     * @return The item stack displayed by this element.
     */
    @Override
    public ItemStack getDisplayItem() {
        return displayEntity.getItemStack();
    }

    /**
     * Sets the item stack to be displayed by this element.
     *
     * @param displayItem The item stack to be displayed.
     */
    @Override
    public void setDisplayItem(ItemStack displayItem) {
        this.displayItem = displayItem;
        displayEntity.setItemStack(displayItem);
    }

    /**
     * Gets the display entity associated with this element.
     *
     * @return The display entity associated with this element.
     */
    @Override
    public ItemDisplay getDisplayEntity() {
        return displayEntity;
    }

    /**
     * Sets the display entity associated with this element.
     *
     * @param displayEntity The display entity to be associated with this element.
     */
    @Override
    public void setDisplayEntity(ItemDisplay displayEntity) {
        this.displayEntity = displayEntity;
    }

    /**
     * Changes the item stack being displayed by this element.
     *
     * @param newItem The new item stack to be displayed.
     */
    public void changeDisplayItem(ItemStack newItem){
        displayItem = newItem;
        displayEntity.setItemStack(displayItem);
    }

    /**
     * Handles interaction with the display element.
     *
     * @param input The input to the interaction.
     */
    @Override
    public void onInteract(Object input) {
        // Interaction logic goes here
    }

    /**
     * Gets the current location of this element.
     *
     * @return The current location of this element.
     */
    @Override
    public Location getCurrentLocation() {
        return super.location;
    }

    /**
     * Sets the current location of this element.
     *
     * @param currentLocation The new location of this element.
     */
    @Override
    public void setCurrentLocation(Location currentLocation) {
        super.location = currentLocation;
    }

    @Override
    public void updateLocation(Location startLocation) {
        Location newLocation = startLocation.clone().add(getAdjustedOffset(offset, startLocation.getYaw()));

        interactionEntity.teleport(newLocation);
        displayEntity.teleport(newLocation.add(0, 0.5, 0));

        this.location = newLocation;
    }

    /**
     * Sets the transformation matrix of the item display entity.
     *
     * @param transformation The transformation to be applied.
     */
    public void setTransformation(Display.Billboard transformation) {
        if (displayEntity != null) {
            displayEntity.setBillboard(transformation);
        }
    }

    /**
     * Sets the brightness of the item display entity.
     *
     * @param brightness The brightness to be set.
     */
    public void setBrightness(Display.Brightness brightness) {
        if (displayEntity != null) {
            displayEntity.setBrightness(brightness);
        }
    }

    /**
     * Sets the shadow radius and strength for the display entity.
     *
     * @param shadowRadius  The shadow radius.
     * @param shadowStrength The shadow strength.
     */
    public void setShadow(float shadowRadius, float shadowStrength) {
        if (displayEntity != null) {
            displayEntity.setShadowRadius(shadowRadius);
            displayEntity.setShadowStrength(shadowStrength);
        }
    }

    /**
     * Sets the view range of the item display entity.
     *
     * @param viewRange The view range to be set.
     */
    public void setViewRange(float viewRange) {
        if (displayEntity != null) {
            displayEntity.setViewRange(viewRange);
        }
    }
}

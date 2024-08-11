package dev.arctic.interactivemenuapi.objects.elements;

import dev.arctic.interactivemenuapi.interfaces.IDisplayElement;
import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Element;
import dev.arctic.interactivemenuapi.objects.Menu;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.bukkit.entity.ItemDisplay;

public class DisplayElement extends Element implements IDisplayElement {

    private ItemStack displayItem;
    private ItemDisplay displayEntity;

    public DisplayElement(Menu parentMenu, Division parentDivision, Vector offset, ItemStack displayItem) {
        super(parentMenu, parentDivision, offset);
        this.displayItem = displayItem;
        initializeDisplayItem();
    }

    private void initializeDisplayItem() {
        displayEntity = location.getWorld().spawn(location, ItemDisplay.class, item -> {
            item.setItemStack(displayItem);
            item.setPersistent(false);
        });
    }

    @Override
    public ItemStack getDisplayItem() {
        return displayEntity.getItemStack();
    }

    @Override
    public void setDisplayItem(ItemStack displayItem) {
        this.displayItem = displayItem;
        displayEntity.setItemStack(displayItem);
    }

    @Override
    public ItemDisplay getDisplayEntity() {
        return displayEntity;
    }

    @Override
    public void setDisplayEntity(ItemDisplay displayEntity) {
        this.displayEntity = displayEntity;
    }

    public void changeDisplayItem(ItemStack newItem){
        displayItem = newItem;
        displayEntity.setItemStack(displayItem);
    }

    @Override
    public void onInteract() {
    }

    @Override
    public Location getCurrentLocation() {
        return super.location;
    }

    @Override
    public void setCurrentLocation(Location currentLocation) {
        super.location = currentLocation;
    }
}

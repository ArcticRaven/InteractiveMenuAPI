package dev.arctic.interactivemenuapi.objects.elements;

import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Element;
import dev.arctic.interactivemenuapi.objects.Menu;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.bukkit.entity.ItemDisplay;

public class DisplayElement extends Element {

    private ItemStack displayItem;
    private ItemDisplay displayEntity;

    public DisplayElement(Menu parentMenu, Division parentDivision, Vector offset, ItemStack displayItem) {
        super(parentMenu, parentDivision, offset);
        this.displayItem = displayItem;
        initializeDisplayItem();
    }

    private void initializeDisplayItem() {
        ItemDisplay itemDisplay = location.getWorld().spawn(location, ItemDisplay.class, item -> {
            item.setItemStack(displayItem);
            item.setPersistent(false);
        });
        displayEntity = itemDisplay;
    }

    public void changeDisplayItem(ItemStack newItem){
        displayItem = newItem;
        displayEntity.setItemStack(displayItem);
    }

    @Override
    public void onInteract() {
    }

    @Override
    public void applyAnimation() {
    }
}

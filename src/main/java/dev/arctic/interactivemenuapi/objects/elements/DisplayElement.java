package dev.arctic.interactivemenuapi.objects.elements;

import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Element;
import dev.arctic.interactivemenuapi.objects.Menu;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.bukkit.entity.ItemDisplay;

public class DisplayElement extends Element {

    private ItemStack displayItem;

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
    }

    @Override
    public void onInteract() {
    }

    @Override
    public void applyAnimation() {
    }
}

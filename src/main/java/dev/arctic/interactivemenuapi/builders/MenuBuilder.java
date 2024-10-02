package dev.arctic.interactivemenuapi.builders;

import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Menu;
import org.bukkit.Location;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

/**
 * Builder class for constructing a Menu.
 */
public class MenuBuilder {

    private Location rootLocation;
    private int timeoutSeconds;
    private Player owner;
    private Interaction anchorEntity;
    private Plugin plugin;
    private List<Division> divisions;
    private UUID menuUUID;
    private long lastInteractionTime;
    private boolean doCleanup;

    public MenuBuilder setRootLocation(Location rootLocation) {
        this.rootLocation = rootLocation;
        return this;
    }

    public MenuBuilder setTimeoutSeconds(int timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
        return this;
    }

    public MenuBuilder setOwner(Player owner) {
        this.owner = owner;
        return this;
    }

    public MenuBuilder setAnchorEntity(Interaction anchorEntity) {
        this.anchorEntity = anchorEntity;
        return this;
    }

    public MenuBuilder setPlugin(Plugin plugin) {
        this.plugin = plugin;
        return this;
    }

    public MenuBuilder setDivisions(List<Division> divisions) {
        this.divisions = divisions;
        return this;
    }

    public MenuBuilder addDivision(Division division) {
        this.divisions.add(division);
        return this;
    }

    public MenuBuilder setMenuUUID(UUID menuUUID) {
        this.menuUUID = menuUUID;
        return this;
    }

    public MenuBuilder setLastInteractionTime(long lastInteractionTime) {
        this.lastInteractionTime = lastInteractionTime;
        return this;
    }

    public MenuBuilder setDoCleanup(boolean doCleanup) {
        this.doCleanup = doCleanup;
        return this;
    }

    // Method to create the anchor entity if not set explicitly
    private Interaction createAnchor(Vector spawnOffset) {
        if (rootLocation.getWorld() == null) return null;
        return rootLocation.getWorld().spawn(rootLocation.add(spawnOffset), Interaction.class, i -> {
            i.setInteractionWidth(0.1f);
            i.setInteractionHeight(0.1f);
            i.setResponsive(false);
        });
    }

    public Menu build() {

        //verify that the owner does not have an existing menu
        if (!owner.getMetadata("InteractiveMenu").isEmpty()) {
            Menu oldmenu = (Menu) owner.getMetadata("InteractiveMenu").get(0);
            if (oldmenu != null) {
                oldmenu.cleanup();
            }
        }

        //create the anchor entity if not set explicitly
        if (this.anchorEntity == null && this.rootLocation != null) {
            this.anchorEntity = createAnchor(new Vector(0, 0, 0));
        }

        Menu menu = new Menu(rootLocation, timeoutSeconds, plugin);

        menu.setOwner(owner);
        menu.setAnchorEntity(anchorEntity);
        menu.setPlugin(plugin);
        menu.setDivisions(divisions);
        menu.setMenuUUID(menuUUID);
        menu.setLastInteractionTime(System.currentTimeMillis());
        menu.setDoCleanup(doCleanup);

        owner.setMetadata("InteractiveMenu", new FixedMetadataValue(plugin, menu));

        return menu;
    }
}

package dev.arctic.interactivemenuapi.builders;

import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Menu;
import dev.arctic.interactivemenuapi.objects.MenuManager;
import org.bukkit.Location;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class MenuBuilder {

    private Location rootLocation;
    private int timeoutSeconds;
    private Player owner;
    private Interaction anchorEntity;
    private Plugin plugin;
    private List<Division> divisions;
    private UUID menuUUID;
    private long lastInteractionTime;
    private boolean isPersistent;
    private boolean visible;

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
        // Initialize to an empty list if the provided list is null
        this.divisions = (divisions != null) ? new CopyOnWriteArrayList<>(divisions) : new CopyOnWriteArrayList<>();
        return this;
    }

    public MenuBuilder addDivision(Division division) {
        if (this.divisions == null) {
            this.divisions = new CopyOnWriteArrayList<>();
        }
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

    public MenuBuilder setPersistent(boolean isPersistent) {
        this.isPersistent = isPersistent;
        return this;
    }

    public MenuBuilder setVisible(boolean visible) {
        this.visible = visible;
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
        if (this.anchorEntity == null && this.rootLocation != null) {
            this.anchorEntity = createAnchor(new Vector(0, 0, 0));
        }

        if (this.divisions == null) {
            this.divisions = new CopyOnWriteArrayList<>();
        }

        Menu menu = new Menu(rootLocation, timeoutSeconds, plugin, owner);
        menu.setAnchorEntity(anchorEntity);
        menu.setPlugin(plugin);
        menu.setDivisions(divisions);
        menu.setMenuUUID(menuUUID);
        menu.setLastInteractionTime(System.currentTimeMillis());
        menu.setPersistent(isPersistent);
        menu.setVisible(visible);

        MenuManager.removeRemnantMenu(owner);

        return menu;
    }
}
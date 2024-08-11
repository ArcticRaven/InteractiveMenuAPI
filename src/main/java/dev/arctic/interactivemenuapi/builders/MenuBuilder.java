package dev.arctic.interactivemenuapi.builders;

import dev.arctic.interactivemenuapi.interfaces.IMenu;
import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Menu;
import org.bukkit.Location;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

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

    public IMenu build() {
        Menu menu = new Menu(rootLocation, timeoutSeconds, plugin);
        menu.setOwner(owner);
        menu.setAnchorEntity(anchorEntity);
        menu.setPlugin(plugin);
        menu.setDivisions(divisions);
        menu.setMenuUUID(menuUUID);
        menu.setLastInteractionTime(lastInteractionTime);
        menu.setDoCleanup(doCleanup);
        return (IMenu) menu;
    }
}

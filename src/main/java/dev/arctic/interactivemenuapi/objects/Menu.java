package dev.arctic.interactivemenuapi.objects;

import dev.arctic.interactivemenuapi.interfaces.IMenu;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Setter
public class Menu implements IMenu {

    // Objects
    @Getter protected Set<Player> owners;
    protected Interaction anchorEntity;
    protected BukkitTask updateTask;
    private Plugin plugin;
    protected List<Division> divisions = new CopyOnWriteArrayList<>();
    protected List<Object> objectStorage = new CopyOnWriteArrayList<>();

    // Data
    protected UUID menuUUID;
    protected Location rootLocation;

    // Function
    private long menuCreationTime; //add menu creation time - use to check if menu is at least 1 minute old in cleanup
    private int timeoutSeconds;
    private long lastInteractionTime;
    private boolean persistent;
    private boolean visible;

    public Menu(Location rootLocation, int timeoutSeconds, Plugin plugin, Player owner) {
        this.rootLocation = rootLocation;
        this.timeoutSeconds = timeoutSeconds;
        this.lastInteractionTime = System.currentTimeMillis();
        this.persistent = false;
        this.plugin = plugin;

        MenuManager.addMenu(this);

        initializeMenu();
    }

    public void addOwner(Player player) {
        owners.add(player);
    }

    public void removeOwner(Player player) {
        owners.remove(player);
    }

    public void initializeMenu() {
        startRunnableUpdateGUI();
    }

    public void updateMenuLocation() {
        for (Division division : divisions) {
            division.updateLocation(anchorEntity.getLocation());
        }
    }

    public void startRunnableUpdateGUI() {
        updateTask = new BukkitRunnable() {
            @Override
            public void run() {
                updateMenuLocation();
            }
        }.runTaskTimer(plugin, 2L, 5L); // Update every 5 ticks
    }

    public void clearMenu() {
        for (Division division : divisions) {
            division.cleanup();
        }
        divisions.clear();
    }

    public void cleanup() {
        clearMenu();

        anchorEntity.remove();
        updateTask.cancel();

        MenuManager.removeMenu(this);
    }

    public void addDivision(Division division) {
        divisions.add(division);
    }
}

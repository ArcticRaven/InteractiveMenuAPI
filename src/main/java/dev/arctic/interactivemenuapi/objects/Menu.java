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
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;


@Getter
@Setter
public class Menu implements IMenu {

    //Objects
    protected Player owner;
    protected Interaction anchorEntity;
    protected BukkitTask updateTask;
    protected BukkitTask cleanupTask;
    private Plugin plugin;
    protected List<Division> divisions = new CopyOnWriteArrayList<>();
    protected List<Object> objectStorage = new CopyOnWriteArrayList<>();

    //Data
    protected UUID menuUUID;
    protected Location rootLocation;

    //function
    private int timeoutSeconds;
    private long lastInteractionTime;
    @Getter private boolean doCleanup;

    public Menu (Location rootLocation, int timeoutSeconds, Plugin plugin) {
        this.rootLocation = rootLocation;
        this.timeoutSeconds = timeoutSeconds;
        this.lastInteractionTime = System.currentTimeMillis();
        this.doCleanup = false;
        this.plugin = plugin;

        MenuManager.addAnchorEntityUUID(anchorEntity.getUniqueId());

        initializeMenu();
    }

    public void initializeMenu() {
        plugin.getLogger().warning("Initializing menu!");
        startRunnableUpdateGUI();
        plugin.getLogger().warning("Starting update task!");
        startCleanupTask();
        plugin.getLogger().warning("Starting cleanup task!");
    }


    private boolean isTimeoutExceeded() {
        return (System.currentTimeMillis() / 1000 - this.lastInteractionTime) >= this.timeoutSeconds;

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

    public void startCleanupTask() {
           cleanupTask = new BukkitRunnable() {
                @Override
                public void run() {
                    if (doCleanup && isTimeoutExceeded()) {
                        cleanup();
                        this.cancel();
                    }
                }
            }.runTaskTimer(plugin, 2L, 20L); // Check every second
        }

    public void clearMenu() {
        for (Division division : divisions) {
            plugin.getLogger().warning("Cleaning up division!");
            division.cleanup();
        }
        divisions.clear();
    }

    public void cleanup() {
        clearMenu();
        plugin.getLogger().warning("executing cleanup!");// Clear all elements first

        anchorEntity.remove();
        MenuManager.removeAnchorEntityUUID(anchorEntity.getUniqueId());

        updateTask.cancel();
        cleanupTask.cancel();
    }

    public void addDivision(Division division) {
        divisions.add(division);
    }
}

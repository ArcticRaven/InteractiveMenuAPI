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
import org.bukkit.util.Vector;

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
    private Plugin plugin;
    protected List<Division> divisions = new CopyOnWriteArrayList<>();
    protected List<Object> objectStorage = new CopyOnWriteArrayList<>();

    //Data
    protected UUID menuUUID;
    protected Location rootLocation;

    //function
    private int timeoutSeconds;
    private long lastInteractionTime;
    private boolean doCleanup;

    public Menu (Location rootLocation, int timeoutSeconds, Plugin plugin) {
        this.rootLocation = rootLocation;
        this.timeoutSeconds = timeoutSeconds;
        this.lastInteractionTime = System.currentTimeMillis() / 1000;
        this.doCleanup = true;
        this.plugin = plugin;
        initializeMenu();
    }

    public void initializeMenu() {
        createAnchor(new Vector(0, 0, 0));
        startRunnableUpdateGUI();
        startCleanupTask();
    }

    public void createAnchor(Vector spawnOffset) {
        if (rootLocation.getWorld() == null) return;
        anchorEntity = rootLocation.getWorld().spawn(rootLocation.add(spawnOffset), Interaction.class, interaction -> {
            interaction.setInteractionWidth(0f);
            interaction.setInteractionHeight(0f);
            interaction.setResponsive(false);
        });
    }

    private boolean isTimeoutExceeded() {
        return (System.currentTimeMillis() / 1000 - lastInteractionTime) >= timeoutSeconds;
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
        }.runTaskTimer(plugin, 0L, 5L); // Update every 5 ticks
    }

    public void startCleanupTask() {
        if (!doCleanup) return;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (isTimeoutExceeded()) {
                    cleanup();
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L); // Check every second
    }

    public void clearMenu() {
        for (Division division : divisions) {
            division.cleanup();
        }
        divisions.clear();
    }

    public void cleanup() {
        clearMenu(); // Clear all elements first
        try {
            anchorEntity.remove();
        } catch (Exception e) {
        }
        if (updateTask != null) {
            updateTask.cancel();
        }
    }

    public void addDivision(Division division) {
        divisions.add(division);
    }
}

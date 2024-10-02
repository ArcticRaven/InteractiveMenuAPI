package dev.arctic.interactivemenuapi.objects;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class MenuManager {

    private static final ArrayList<Menu> ActiveMenus = new ArrayList<>();
    @Getter
    static BukkitTask CleanupTask;

    /**
     * Adds a menu to the list of active menus.
     * @param menu The menu to add.
     */
    public static void addMenu(Menu menu) {
        ActiveMenus.add(menu);
    }

    /**
     * Removes a menu from the list of active menus.
     * @param menu The menu to remove.
     */
    public static void removeMenu(Menu menu) {
        ActiveMenus.remove(menu);
    }

    /**
     * Manually cleans up all active menus.
     * This is intended to be called on plugin shutdown.
     */
    public static void cleanupMenus() {
        for (Menu menu : new ArrayList<>(ActiveMenus)) {
            menu.cleanup();
        }
        ActiveMenus.clear();
    }

    /**
     * Cleans up only expired menus based on their timeout and cleanup settings.
     */
    public static void cleanupExpiredMenus() {
        long currentTime = System.currentTimeMillis();

        for (Menu menu : new ArrayList<>(ActiveMenus)) {
            if (menu.isDoCleanup() &&
                    currentTime - menu.getLastInteractionTime() > menu.getTimeoutSeconds() * 1000L) {
                menu.cleanup();
                removeMenu(menu);
            }
        }
    }

    /**
     * Starts the async cleanup task to clean up expired menus every second.
     * @param plugin The plugin instance to schedule the task with.
     */
    public static void startCleanupTask(Plugin plugin) {
        CleanupTask = Bukkit.getScheduler().runTaskTimer(plugin, MenuManager::cleanupExpiredMenus, 20L, 20L);
    }
}

package dev.arctic.interactivemenuapi.objects;

import dev.arctic.interactivemenuapi.objects.Menu;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;
import java.util.Set;

public class MenuManager {

    private static final Set<Menu> ActiveMenus = new HashSet<>();
    @Getter
    public static BukkitTask CleanupTask;

    public static void addMenu(Menu menu) {
        ActiveMenus.add(menu);
    }

    public static void removeMenu(Menu menu) {
        ActiveMenus.remove(menu);
    }

    public static void cleanupMenus() {
        for (Menu menu : new HashSet<>(ActiveMenus)) {
            if (menu != null) {
                menu.cleanup();
            }
        }
        ActiveMenus.clear();
    }

    public static void cleanupExpiredMenus() {
        long currentTime = System.currentTimeMillis();

        for (Menu menu : new HashSet<>(ActiveMenus)) {
            if (menu != null && menu.isDoCleanup() && currentTime - menu.getLastInteractionTime() > menu.getTimeoutSeconds() * 1000L) {
                menu.cleanup();
                removeMenu(menu);
            }
        }
    }

    public static void startCleanupTask(Plugin plugin) {
        CleanupTask = Bukkit.getScheduler().runTaskTimer(plugin, MenuManager::cleanupExpiredMenus, 20L, 20L);
    }
}

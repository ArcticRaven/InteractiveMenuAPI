package dev.arctic.interactivemenuapi.objects;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class MenuManager {

    private static final Set<Menu> ActiveMenus = new HashSet<>();

    public static void addMenu(Menu menu) {
        ActiveMenus.add(menu);
    }

    public static void removeMenu(Menu menu) {
        ActiveMenus.remove(menu);
    }

    public static Menu getMenuByUUID(UUID uuid) {
        for (Menu menu : ActiveMenus) {
            if (menu.getMenuUUID().equals(uuid)) {
                return menu;
            }
        }
        return null;
    }

    public static void startAutomaticCleanup(Plugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Iterator<Menu> iterator = ActiveMenus.iterator();
                while (iterator.hasNext()) {
                    Menu menu = iterator.next();
                    if (menu == null || menu.getAnchorEntity() == null) {
                        iterator.remove();
                    } else {
                        long currentTime = System.currentTimeMillis();
                        long menuAge = (currentTime - menu.getMenuCreationTime()) / 1000;
                        long lastInteractionAge = (currentTime - menu.getLastInteractionTime()) / 1000;

                        if (!menu.isPersistent() && menuAge > menu.getTimeoutSeconds() && lastInteractionAge > menu.getTimeoutSeconds()) {
                            menu.cleanup();
                            iterator.remove();
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 600L); // Run every 30 seconds (600 ticks)
    }
}
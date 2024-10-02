package dev.arctic.interactivemenuapi.objects;

import java.util.HashSet;
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

    public static void cleanupMenus() {
        for (Menu menu : ActiveMenus) {
            menu.cleanup();
        }
    }

    public static Menu getMenuByUUID(UUID uuid) {
        for (Menu menu : ActiveMenus) {
            if (menu.getMenuUUID().equals(uuid)) {
                return menu;
            }
        }
        return null;
    }
}

package dev.arctic.interactivemenuapi.objects;

import java.util.ArrayList;

public class MenuManager {

    private static final ArrayList<Menu> ActiveMenus = new ArrayList<>();

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
}

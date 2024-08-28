package dev.arctic.interactivemenuapi.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Interaction;

import java.util.ArrayList;
import java.util.UUID;

public class MenuManager {

    private static final ArrayList<UUID> AnchorEntityUUIDs = new ArrayList<>();

    public static void addAnchorEntityUUID(UUID uuid) {
        AnchorEntityUUIDs.add(uuid);
    }

    public static void removeAnchorEntityUUID(UUID uuid) {
        AnchorEntityUUIDs.remove(uuid);
    }

    public static void cleanupAnchorEntities() {
        for (UUID uuid : AnchorEntityUUIDs) {
            Interaction interaction = (Interaction) Bukkit.getEntity(uuid);
            if (interaction != null) {
                interaction.remove();
            }
        }
    }
}

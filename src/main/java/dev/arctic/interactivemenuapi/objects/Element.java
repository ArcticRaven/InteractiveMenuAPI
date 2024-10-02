package dev.arctic.interactivemenuapi.objects;

import dev.arctic.interactivemenuapi.interfaces.IElement;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.BiConsumer;

//TODO - Add methods to adjust the interaction entity sizes as part of the Builder

@Getter
@Setter
public abstract class Element implements IElement {

    protected Menu parentMenu;
    protected Division parentDivision;
    protected Location location;
    protected Vector offset;
    protected Interaction interactionEntity;
    protected TextDisplay textDisplayEntity;
    private BiConsumer<Player, Object> onInteract;
    private boolean hiddenFromPlayers;

    public Element(Menu parentMenu, Division parentDivision, Vector offset, boolean hiddenFromPlayers) {
        this.parentMenu = parentMenu;
        this.parentDivision = parentDivision;
        this.offset = offset;
        this.hiddenFromPlayers = hiddenFromPlayers;
        this.location = parentDivision.getCurrentLocation().clone().add(offset);
        initializeEntities();
    }

    protected void initializeEntities() {
        Plugin plugin = parentMenu.getPlugin();
        Player owner = parentMenu.getOwner();

        this.interactionEntity = location.getWorld().spawn(location, Interaction.class, interaction -> {
            interaction.setPersistent(false);
            interaction.setMetadata("InteractiveMenu", new FixedMetadataValue(plugin, this));


            if (hiddenFromPlayers) {
                interaction.setVisibleByDefault(false);
                owner.showEntity(plugin, interaction);
            }
        });

        this.textDisplayEntity = location.getWorld().spawn(location, TextDisplay.class, textDisplay -> {
            textDisplay.setPersistent(false);
            textDisplay.setVisibleByDefault(hiddenFromPlayers);

            if (hiddenFromPlayers) {
                textDisplay.setVisibleByDefault(false);
                owner.showEntity(plugin, textDisplay);
            }
        });
    }

    public void showToPlayer(UUID playerUUID) {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player != null && player.isOnline()) {
            Plugin plugin = parentMenu.getPlugin();
            player.showEntity(plugin, interactionEntity);
            player.showEntity(plugin, textDisplayEntity);
        }
    }

    public void showToPlayers(ArrayList<UUID> playerUUIDs) {
        Plugin plugin = parentMenu.getPlugin();
        for (UUID uuid : playerUUIDs) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null && player.isOnline()) {
                player.showEntity(plugin, interactionEntity);
                player.showEntity(plugin, textDisplayEntity);
            }
        }
    }

    public void updateLocation(Location startLocation) {
        Location newLocation = startLocation.clone().add(getAdjustedOffset(offset, startLocation.getYaw()));

        interactionEntity.teleport(newLocation);
        textDisplayEntity.teleport(newLocation);

        this.location = newLocation;
    }

    public void setOpacity(int opacity) {
        if (textDisplayEntity != null) {
            if (opacity == 0) {
                textDisplayEntity.text(Component.text(" "));
            }
            textDisplayEntity.setBackgroundColor(Color.fromARGB(opacity, 0, 0, 0));
        }
    }

    public void cleanup() {
        if (interactionEntity != null && !interactionEntity.isDead()) {
            interactionEntity.remove();
        }
        if (textDisplayEntity != null && !textDisplayEntity.isDead()) {
            textDisplayEntity.remove();
        }
    }

    protected Vector getAdjustedOffset(Vector offset, float yaw) {
        double radians = Math.toRadians(yaw);

        double cos = Math.cos(radians);
        double sin = Math.sin(radians);

        double x = offset.getX() * cos - offset.getZ() * sin;
        double z = offset.getX() * sin + offset.getZ() * cos;

        return new Vector(x, offset.getY(), z);
    }

    public void setExternalFunction(BiConsumer<Player, Object> onInteract) {
        this.onInteract = onInteract;
    }

    public void doExternalFunction(Player player, Object input) {
        getParentMenu().setLastInteractionTime(System.currentTimeMillis());
        if (onInteract != null) {
            onInteract.accept(player, input);
        }
    }

    public abstract void onInteract(Object input);

    public abstract Location getCurrentLocation();

    public abstract void setCurrentLocation(Location currentLocation);
}

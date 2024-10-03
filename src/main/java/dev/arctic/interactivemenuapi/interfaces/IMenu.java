package dev.arctic.interactivemenuapi.interfaces;

import dev.arctic.interactivemenuapi.objects.Division;
import org.bukkit.Location;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Interface representing a Menu in the Interactive Menu API.
 * This interface defines the core functionalities required for a Menu, including initialization, updating, and cleanup.
 *
 * <p>Example Usage:</p>
 * <pre>
 * {@code
 * IMenu menu = new MenuImplementation(new Location(world, x, y, z), 30);
 * menu.initializeMenu();
 * menu.updateMenuLocation();
 * menu.cleanup();
 * }
 * </pre>
 */
public interface IMenu {

    /**
     * Initializes the menu by setting up the anchor entity and starting necessary tasks.
     */
    void initializeMenu();

    /**
     * Updates the locations of all divisions within this menu.
     */
    void updateMenuLocation();

    /**
     * Starts the GUI update task, which periodically updates the menu.
     */
    void startRunnableUpdateGUI();

    /**
     * Clears all divisions and elements from the menu.
     */
    void clearMenu();

    /**
     * Cleans up the menu by removing the anchor entity and canceling any active tasks.
     */
    void cleanup();

    /**
     * Adds a player as an owner of this menu.
     *
     * @param player The player to add as an owner.
     */
    void addOwner(Player player);

    /**
     * Removes a player from the owners of this menu.
     *
     * @param player The player to remove from owners.
     */
    void removeOwner(Player player);

    /**
     * Gets the players who own this menu.
     *
     * @return A set of players who own the menu.
     */
    Set<Player> getOwners();

    /**
     * Gets the anchor entity used by this menu.
     *
     * @return The anchor entity.
     */
    Interaction getAnchorEntity();

    /**
     * Sets the anchor entity used by this menu.
     *
     * @param anchorEntity The anchor entity.
     */
    void setAnchorEntity(Interaction anchorEntity);

    /**
     * Gets the plugin associated with this menu.
     *
     * @return The plugin.
     */
    Plugin getPlugin();

    /**
     * Sets the plugin that owns this menu.
     *
     * @param plugin The plugin.
     */
    void setPlugin(Plugin plugin);

    /**
     * Gets the list of divisions within this menu.
     *
     * @return The list of divisions.
     */
    List<Division> getDivisions();

    /**
     * Sets the list of divisions within this menu.
     *
     * @param divisions The list of divisions.
     */
    void setDivisions(List<Division> divisions);

    /**
     * Adds a division to this menu.
     *
     * @param division The division to add.
     */
    void addDivision(Division division);

    /**
     * Gets the UUID of this menu.
     *
     * @return The UUID.
     */
    UUID getMenuUUID();

    /**
     * Sets the UUID of this menu.
     *
     * @param menuUUID The UUID.
     */
    void setMenuUUID(UUID menuUUID);

    /**
     * Gets the root location of this menu.
     *
     * @return The root location.
     */
    Location getRootLocation();

    /**
     * Sets the root location of this menu.
     *
     * @param rootLocation The root location.
     */
    void setRootLocation(Location rootLocation);

    /**
     * Gets the timeout duration in seconds for this menu.
     *
     * @return The timeout duration in seconds.
     */
    int getTimeoutSeconds();

    /**
     * Sets the timeout duration in seconds for this menu.
     *
     * @param timeoutSeconds The timeout duration in seconds.
     */
    void setTimeoutSeconds(int timeoutSeconds);

    /**
     * Gets the time of the last interaction with this menu.
     *
     * @return The time of the last interaction in milliseconds.
     */
    long getLastInteractionTime();

    /**
     * Sets the time of the last interaction with this menu.
     *
     * @param lastInteractionTime The time of the last interaction in milliseconds.
     */
    void setLastInteractionTime(long lastInteractionTime);

    /**
     * Gets the menu creation time.
     *
     * @return The menu creation time in milliseconds.
     */
    long getMenuCreationTime();

    /**
     * Sets the menu creation time.
     *
     * @param menuCreationTime The menu creation time in milliseconds.
     */
    void setMenuCreationTime(long menuCreationTime);

    /**
     * Checks whether the menu is persistent.
     *
     * @return True if the menu is persistent, false otherwise.
     */
    boolean isPersistent();

    /**
     * Sets whether the menu is persistent.
     *
     * @param persistent True if the menu should be persistent, false otherwise.
     */
    void setPersistent(boolean persistent);

    /**
     * Checks whether the menu is visible to non-owners.
     *
     * @return True if the menu is visible to non-owners, false otherwise.
     */
    boolean isVisible();

    /**
     * Sets whether the menu is visible to non-owners.
     *
     * @param visible True if the menu should be visible to non-owners, false otherwise.
     */
    void setVisible(boolean visible);
}
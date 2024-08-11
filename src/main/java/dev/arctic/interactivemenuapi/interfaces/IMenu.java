package dev.arctic.interactivemenuapi.interfaces;

import dev.arctic.interactivemenuapi.objects.Division;
import org.bukkit.Location;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.List;
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
     * Creates an anchor entity at the specified spawn offset.
     *
     * @param spawnOffset The vector offset for spawning the anchor entity.
     */
    void createAnchor(Vector spawnOffset);

    /**
     * Updates the anchor entity's location.
     */
    void updateAnchorLocation();

    /**
     * Updates the locations of all divisions within this menu.
     */
    void updateMenuLocation();

    /**
     * Starts the GUI update task, which periodically updates the menu.
     */
    void startRunnableUpdateGUI();

    /**
     * Starts the cleanup task, which checks for menu timeout and cleans up if necessary.
     */
    void startCleanupTask();

    /**
     * Clears all divisions and elements from the menu.
     */
    void clearMenu();

    /**
     * Cleans up the menu by removing the anchor entity and canceling any active tasks.
     */
    void cleanup();

    /**
     * Gets the player who owns this menu.
     *
     * @return The player who owns the menu.
     */
    Player getOwner();

    /**
     * Sets the player who owns this menu.
     *
     * @param owner The player who owns the menu.
     */
    void setOwner(Player owner);

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
     * Sets the plugin associated with this menu.
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
     * Checks whether cleanup is enabled for this menu.
     *
     * @return True if cleanup is enabled, false otherwise.
     */
    boolean isDoCleanup();

    /**
     * Sets whether cleanup is enabled for this menu.
     *
     * @param doCleanup True if cleanup should be enabled, false otherwise.
     */
    void setDoCleanup(boolean doCleanup);
}

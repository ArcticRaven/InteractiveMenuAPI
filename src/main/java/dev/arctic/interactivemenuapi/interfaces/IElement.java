package dev.arctic.interactivemenuapi.interfaces;

import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Menu;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.function.BiConsumer;

/**
 * Interface representing a generic Element in the Interactive Menu API.
 * All specific element types should implement this interface or its sub-interfaces.
 */
public interface IElement {
    /**
     * Updates the location of the element relative to its parent division's location.
     *
     * @param divisionLocation The location of the parent division.
     */
    void updateLocation(Location divisionLocation);

    /**
     * Cleans up the element, removing any associated entities and freeing resources.
     */
    void cleanup();

    /**
     * Gets the parent menu of this element.
     *
     * @return The parent menu.
     */
    Menu getParentMenu();

    /**
     * Sets the parent menu of this element.
     *
     * @param parentMenu The parent menu.
     */
    void setParentMenu(Menu parentMenu);

    /**
     * Gets the parent division of this element.
     *
     * @return The parent division.
     */
    Division getParentDivision();

    /**
     * Sets the parent division of this element.
     *
     * @param parentDivision The parent division.
     */
    void setParentDivision(Division parentDivision);

    /**
     * Gets the current location of this element.
     *
     * @return The current location.
     */
    Location getLocation();

    /**
     * Sets the current location of this element.
     *
     * @param location The current location.
     */
    void setLocation(Location location);

    /**
     * Sets the action to be performed when this element is interacted with.
     *
     * @param onInteract BiConsumer that takes a Player and an Object (input).
     */
    void setExternalFunction(BiConsumer<Player, Object> onInteract);

    /**
     * Executes the action defined by the BiConsumer when this element is interacted with.
     *
     * @param player The player interacting with the element.
     * @param input The input object related to the interaction.
     */
    void doExternalFunction(Player player, Object input);
}

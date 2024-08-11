package dev.arctic.interactivemenuapi.interfaces;

import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Menu;
import org.bukkit.Location;
import org.bukkit.util.Vector;

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
     * Handles interactions with the element.
     */
    void onInteract();

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
    Location getCurrentLocation();

    /**
     * Sets the current location of this element.
     *
     * @param currentLocation The current location.
     */
    void setCurrentLocation(Location currentLocation);

    /**
     * Gets the offset of this element relative to the parent division's location.
     *
     * @return The offset.
     */
    Vector getOffset();

    /**
     * Sets the offset of this element relative to the parent division's location.
     *
     * @param offset The offset.
     */
    void setOffset(Vector offset);
}


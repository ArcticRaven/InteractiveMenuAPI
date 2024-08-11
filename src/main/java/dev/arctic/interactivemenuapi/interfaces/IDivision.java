package dev.arctic.interactivemenuapi.interfaces;

import dev.arctic.interactivemenuapi.animation.AnimationType;
import dev.arctic.interactivemenuapi.objects.Element;
import dev.arctic.interactivemenuapi.objects.Menu;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.List;

/**
 * Interface representing a Division in the Interactive Menu API.
 * This interface defines the core functionalities required for a Division, including location updates, animation handling, and cleanup.
 *
 * <p>Example Usage:</p>
 * <pre>
 * {@code
 * IDivision division = new DivisionImplementation(menu, location, offset, animationType, stepper);
 * division.updateLocation(menu.getRootLocation());
 * division.cleanup();
 * }
 * </pre>
 */
public interface IDivision {

    /**
     * Updates the location of the division relative to the root menu location.
     *
     * @param rootMenuLocation The root location of the parent menu.
     */
    void updateLocation(Location rootMenuLocation);

    /**
     * Applies the division's animation based on its current animation type and stepper.
     */
    void applyAnimation();

    /**
     * Cleans up the division by removing all its elements and releasing resources.
     */
    void cleanup();

    /**
     * Gets the parent menu of this division.
     *
     * @return The parent menu.
     */
    Menu getParentMenu();

    /**
     * Sets the parent menu of this division.
     *
     * @param parentMenu The parent menu.
     */
    void setParentMenu(Menu parentMenu);

    /**
     * Gets the current location of this division.
     *
     * @return The current location.
     */
    Location getCurrentLocation();

    /**
     * Sets the current location of this division.
     *
     * @param currentLocation The current location.
     */
    void setCurrentLocation(Location currentLocation);

    /**
     * Gets the offset of this division relative to the parent menu's root location.
     *
     * @return The offset.
     */
    Vector getOffset();

    /**
     * Sets the offset of this division relative to the parent menu's root location.
     *
     * @param offset The offset.
     */
    void setOffset(Vector offset);

    /**
     * Gets the animation type of this division.
     *
     * @return The animation type.
     */
    AnimationType getAnimationType();

    /**
     * Sets the animation type of this division.
     *
     * @param animationType The animation type.
     */
    void setAnimationType(AnimationType animationType);

    /**
     * Gets the stepper value used for the division's animation.
     *
     * @return The stepper value.
     */
    double getAnimationStepper();

    /**
     * Sets the stepper value used for the division's animation.
     *
     * @param stepper The stepper value.
     */
    void setAnimationStepper(double stepper);

    /**
     * Gets the list of elements contained within this division.
     *
     * @return The list of elements.
     */
    List<Element> getElements();

    /**
     * Sets the list of elements contained within this division.
     *
     * @param elements The list of elements.
     */
    void setElements(List<Element> elements);
}

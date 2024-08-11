package dev.arctic.interactivemenuapi.events;

import dev.arctic.interactivemenuapi.interfaces.IMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Event that is called when a player interacts with an element in an InteractiveMenu.
 */
public class MenuInteractionEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final IMenu menu;
    private final String elementFlag;
    private final Player player;

    /**
     * Constructs a new MenuInteractionEvent.
     *
     * @param menu The InteractiveMenu that contains the interacted element.
     * @param elementFlag The string flag of the interacted element.
     * @param player The player who interacted with the element.
     */
    public MenuInteractionEvent(IMenu menu, String elementFlag, Player player) {
        this.menu = menu;
        this.elementFlag = elementFlag;
        this.player = player;
    }

    /**
     * Gets the InteractiveMenu associated with this event.
     *
     * @return The InteractiveMenu.
     */
    public IMenu getMenu() {
        return menu;
    }

    /**
     * Gets the string flag of the interacted element.
     *
     * @return The element's string flag.
     */
    public String getElementFlag() {
        return elementFlag;
    }

    /**
     * Gets the player who interacted with the element.
     *
     * @return The player who triggered the event.
     */
    public Player getPlayer() {
        return player;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
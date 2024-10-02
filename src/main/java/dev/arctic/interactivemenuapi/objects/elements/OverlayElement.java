package dev.arctic.interactivemenuapi.objects.elements;

import dev.arctic.interactivemenuapi.interfaces.IOverlayElement;
import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Element;
import dev.arctic.interactivemenuapi.objects.Menu;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.bukkit.scheduler.BukkitRunnable;


public class OverlayElement extends Element implements IOverlayElement {

    private boolean interactToRemove;
    private long displayDuration;
    private Component text;

    public OverlayElement(Menu parentMenu, Division parentDivision, Vector offset, boolean visible, boolean interactToRemove, long displayDuration, Component text) {
        super(parentMenu, parentDivision, offset.add(new Vector(0, 0.1, 0)), visible);
        this.interactToRemove = interactToRemove;
        this.displayDuration = displayDuration;
        this.text = text;

        if (displayDuration > 0) {
            startDisplayTimer();
        }
    }

    private void startDisplayTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                cleanup();
            }
        }.runTaskLater(parentMenu.getPlugin(), displayDuration);
    }

    @Override
    public void onInteract(Object input) {
        if (interactToRemove) {
            cleanup();
        }
    }

    public void setText(){
        textDisplayEntity.text(text);
    }


    @Override
    public Location getCurrentLocation() {
        return this.location;
    }

    @Override
    public void setCurrentLocation(Location currentLocation) {
        this.location = currentLocation;
    }

    @Override
    public boolean isInteractToRemove() {
        return this.interactToRemove;
    }

    @Override
    public void setInteractToRemove(boolean interactToRemove) {
        this.interactToRemove = interactToRemove;
    }

    @Override
    public long getDisplayDuration() {
        return this.displayDuration;
    }

    @Override
    public void setDisplayDuration(long displayDuration) {
        this.displayDuration = displayDuration;
    }

    @Override
    public void setText(Component text) {
        textDisplayEntity.text(text);
    }
}

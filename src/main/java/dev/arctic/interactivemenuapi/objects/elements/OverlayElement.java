package dev.arctic.interactivemenuapi.objects.elements;

import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Element;
import dev.arctic.interactivemenuapi.objects.Menu;
import net.kyori.adventure.text.Component;
import org.bukkit.util.Vector;
import org.bukkit.scheduler.BukkitRunnable;


public class OverlayElement extends Element {

    private boolean interactToRemove;
    private long displayDuration;
    private Component text;

    public OverlayElement(Menu parentMenu, Division parentDivision, Vector offset, boolean interactToRemove, long displayDuration, Component text) {
        super(parentMenu, parentDivision, offset.add(new Vector(0, 0.1, 0)));
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
    public void onInteract() {
        if (interactToRemove) {
            cleanup();
        }
    }

    public void setText(){
        textDisplayEntity.text(text);
    }

    @Override
    public void applyAnimation() {
    }
}

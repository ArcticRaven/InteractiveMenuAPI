package dev.arctic.interactivemenuapi.builders;

import dev.arctic.interactivemenuapi.interfaces.IMenu;
import dev.arctic.interactivemenuapi.objects.Menu;
import org.bukkit.Location;

public class MenuBuilder {

    private Location rootLocation;
    private int timeoutSeconds;

    public MenuBuilder setRootLocation(Location rootLocation) {
        this.rootLocation = rootLocation;
        return this;
    }

    public MenuBuilder setTimeoutSeconds(int timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
        return this;
    }

    public IMenu build() {
        return (IMenu) new Menu(rootLocation, timeoutSeconds);
    }
}

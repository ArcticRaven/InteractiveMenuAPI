package dev.arctic.interactivemenuapi.builders;

import dev.arctic.interactivemenuapi.animation.AnimationType;
import dev.arctic.interactivemenuapi.interfaces.IDivision;
import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Element;
import dev.arctic.interactivemenuapi.objects.Menu;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DivisionBuilder {

    private Menu parentMenu;
    private Location initialLocation;
    private Vector offset;
    private AnimationType animationType = AnimationType.NONE;
    private double animationStepper = 0.0;
    private List<Element> elements = new CopyOnWriteArrayList<>();

    public DivisionBuilder setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
        return this;
    }

    public DivisionBuilder setInitialLocation(Location initialLocation) {
        this.initialLocation = initialLocation;
        return this;
    }

    public DivisionBuilder setOffset(Vector offset) {
        this.offset = offset;
        return this;
    }

    public DivisionBuilder setAnimationType(AnimationType animationType) {
        this.animationType = animationType;
        return this;
    }

    public DivisionBuilder setAnimationStepper(double animationStepper) {
        this.animationStepper = animationStepper;
        return this;
    }

    public DivisionBuilder setElements(List<Element> elements) {
        this.elements = elements;
        return this;
    }

    public DivisionBuilder addElement(Element element) {
        this.elements.add(element);
        return this;
    }

    public IDivision build() {
        Division division = new Division(parentMenu, initialLocation, offset, animationType, animationStepper);
        division.setElements(elements);
        return (IDivision) division;
    }
}

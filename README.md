# InteractiveMenuAPI
[![](https://jitpack.io/v/ArcticRaven/InteractiveMenuAPI.svg)](https://jitpack.io/#ArcticRaven/InteractiveMenuAPI)

An API to create Display Entity User Interfaces similar to web pages.

## Features

- Create interactive menus with buttons, text, and images.
- Create and implement custom animations for menus.
- Utilizes Components for modern RCS (RGB, Unicode, and Styles)
- Supports multiple pages and menus.

This resource is intended to interact similar to web-design, with complex pages and interactions.

## Implementation

This API is designed for Spigot and its derivatives. It is currently not available for other server software.

### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.ArcticRaven</groupId>
    <artifactId>InteractiveMenuAPI</artifactId>
    <version>Tag</version>
</dependency>
```

### Gradle
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.ArcticRaven:InteractiveMenuAPI:Tag'
}
```

## Usage
This is the tricky part! First, some intended design. 

### Menu
Menus are the "top level" of the API and house the main information necessary for universal menu control
and implementations. 

Menus include a generic "ObjectStorage" Array to store custom objects and data necessary for integration with
other (your) resources without the need to complex child classes. 

The "Menu" object reference is stored inside of Divisions and Elements. Menus can ONLY contain Divisions.

### Division
Divisions are the "middle level" and allow control over collections of Elements as a whole. This allows generic
animations or positioning of Elements within a Division. Divisions house a reference to both the Parent Menu and the
Elements within the Division. 

Divisions are intended to be the only object that can move Forward or Backwards in the Menu, but I didn't actually restrict
elements from being able to do this either...have fun?

### Element
Elements are the "bottom level" and are the most basic form of interaction. Elements are the only objects that can be
interacted with directly by the player. The concrete "Element" class is NOT intended to be used directly. Instead use
the Child classes.
#### Child Classes

| **Class Name**    | **Description**                                                                                                                                     | **Interaction**                                                                                   | **Special Features**                                                                                     |
|-------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| `DisplayElement`  | Renders `ItemStack`s at the element's position.                                                                                                     | Contains `onInteract()` method to change the rendered `ItemStack`.                                | Can dynamically update the displayed `ItemStack` on interaction.                                                    |
| `OverlayElement`  | An interactable "tooltip" style element that defaults to 0.1 distance away from the division root location (towards the player).                    | Can decay after a specified number of ticks or on interaction.                                    | Provides options for timed decay or interaction-based removal.                                                      |
| `TextElement`     | Displays a `Component` text object.                                                                                                                 | None.                                                                                             | Used solely for displaying static text with no interaction capabilities.                                            |
| `ToggleElement`   | Switches between primary and secondary text on interaction. Can be set to automatically revert back to primary text after a countdown.              | Interaction toggles between primary and secondary text.                                           | Contains a countdown feature to revert back to the primary text after a specified duration.                         |

## In Code this time...

### Example Menu Implementation
Note that this code is also available inside of the "examplePlugin" module. 

```java
package dev.arctic.exampleMenu.menu;

import dev.arctic.exampleMenu.ExampleMenu;
import dev.arctic.interactivemenuapi.builders.*;
import dev.arctic.interactivemenuapi.interfaces.*;
import dev.arctic.interactivemenuapi.animation.AnimationType;
import dev.arctic.interactivemenuapi.objects.Division;
import dev.arctic.interactivemenuapi.objects.Menu;
import dev.arctic.interactivemenuapi.objects.elements.DisplayElement;
import dev.arctic.interactivemenuapi.objects.elements.TextElement;
import dev.arctic.interactivemenuapi.objects.elements.ToggleElement;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class ExampleMenuUI {

    public static IMenu createMenu(Location rootLocation, Player owner) {

        /*
            Build your Menu in this order
            { Menu -> Division -> Elements -> Add Elements to Division -> Add Division to Menu -> Return Menu}
            This will prevent null values when creating your elements.
         */


        // Create the menu and add the division
        Menu menu = new MenuBuilder()
                .setRootLocation(rootLocation)
                .setPlugin(ExampleMenu.getPlugin(ExampleMenu.class))
                .setDoCleanup(true)
                .setOwner(owner)
                .setDivisions(new ArrayList<>())
                .setTimeoutSeconds(60)
                .build();

        // Create division
        Division division = new DivisionBuilder()
                .setAnimationType(AnimationType.LEFT)
                .setAnimationStepper(0.1f)
                .setElements(new ArrayList<>())
                .setInitialLocation(rootLocation) //this should usually just be the root location, then apply offset
                .setOffset(new Vector(0,0,0))
                .setParentMenu(menu)
                .build();

        // Create display element
        DisplayElement displayElement = new DisplayElementBuilder()
                .setDisplayItem(new ItemStack(Material.DIAMOND))
                .setParentMenu(menu)
                .setParentDivision(division)
                .setOffset(new Vector(0, 0, 1))
                .build();

        // Create text element
        TextElement textElement = new TextElementBuilder()
                .setText(Component.text("Test Text Element :D"))
                .setParentMenu(menu)
                .setParentDivision(division)
                .setOffset(new Vector(0, 0, 0.5))
                .build();

        // Create toggle element
        ToggleElement toggleElement = new ToggleElementBuilder()
                .setPrimaryText(Component.text("Primary Text"))
                .setSecondaryText(Component.text("Secondary Text"))
                .setPressAnimationType(AnimationType.PRESSED)
                .setPressAnimationStepper(0.1f)
                .setOffset(new Vector(0, 0, 0))
                .setParentMenu(menu)
                .setParentDivision(division)
                .build();


        /*
            Add the elements to the division
            This is important to do before adding the division to the menu
            Essentially reversing the order of creation :D
         */
        division.addElement(toggleElement);
        division.addElement(textElement);
        division.addElement(displayElement);

        menu.addDivision(division);

        return menu;
    }
}
```
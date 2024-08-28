# InteractiveMenuAPI
[![](https://jitpack.io/v/ArcticRaven/InteractiveMenuAPI.svg)](https://jitpack.io/#ArcticRaven/InteractiveMenuAPI)

An API to create Display Entity User Interfaces similar to web pages.

To view an example plugin - check this out: [InteractiveMenuAPI Example Plugin](https://github.com/ArcticRaven/InteractiveMenuAPI-ExamplePlugin)
Its filled with commentary and tips on how to get started and use the API. 

For support - please use Github Issues or (preferably) join my support discord with the following invite link 
[Discord](https://discord.gg/xErSpeWa8d)

## Features

- Create interactive menus with buttons, text, and images.
- Create and implement custom animations for menus.
- Utilizes Components for modern RCS (RGB, Unicode, and Styles)
- Supports multiple pages and menus.

This resource is intended to interact similar to web-design, with complex pages and interactions.

## Implementation

This API is designed for Spigot and its derivatives. It is currently not available for other server software.
This API also uses Java 21 and will NOT support any versions earlier than 1.21.x

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

The best place to get a good look at how to use is the example [InteractiveMenuAPI Example Plugin](https://github.com/ArcticRaven/InteractiveMenuAPI-ExamplePlugin). 
I've filled it with comments and kept the example pretty straightforward. 

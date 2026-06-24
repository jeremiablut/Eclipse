# **Eclipse**
This _Clientside_ Mod is meant as a collection of client-side utilities. This mod is still work in progress.
## Features:
>- **Config GUI**, open via **COMMA** , more info below
>- **Show FPS count** (default to false), Key Bind: **F6**, *configurable in GUI*
>- **Show Ping** (default to true), *configurable in GUI*
>- **Show CPS** (default to true), *configurable in GUI*
>- **Enable pvp mode** (default to true), Command: **/pvp**, more info below
>- **Toggle autosprint** (default to true), Key Bind: **Left Control**, *configurable in GUI*
>- **Toggle display of autosprint** (default to true), *configurable in GUI*
>- **Toggle Freecam** (default to false), Key Bind: **F4**, Command: **/freecam toggle**, more below
>- **Timer+Expansion** (default to false), Command: **/timer**, *configurable in GUI*, more Info below
>- **Toggle nofog** (default to true), *configurable in GUI*
>- **Toggle gamma** (default to true), Key Bind: **J**, *configurable in GUI*
>- **Auto Marker for players using this mod** (with different Ranks --> upcoming), more info below
## Commands:
>- **/pvp** enables pvp mode, more info below (default to true)
>- **/freecam toggle** toggles Freecam, more info below (default to false)
>- **/timer + expansion** more Info below (default to false)
## Hotkeys:
>- **COMMA** Toggles Config Menu
>- **F6** Toggles Fps
>- **F4** Toggles Freecam
>- **Left Control** Toggles Sprint
>- **J** Toggles Gamma

## Config GUI
The Config GUI adds a menu in Minecraft, that opens by pressing **COMMA** , in which the things in the List below are configurable.
#### Move
This opens a under menu in which the Widgets like the timer and the FPS count can be moved around the screen.
#### FPS
> - **Toggle FPS** (shows FPS count)
#### Timer
> - **Toggle Timer** (toggles timer)
> - **Start Timer** (starts timer)
> - **Stop Timer** (stop timer)
> - **Restart Timer** (restarts timer)
#### CPS
> - **Toggle CPS** (toggles CPS)
#### Ping
> - **Ping Self** (toggles your ping)
> - **Ping TAB** (toggles Ping in TAB List)
#### NoFog
> - **Nofog** (toggles Nofog)
#### Sprint
> - **Auto Sprint** (toggles Auto Sprint)
> - **Visual Sprint** (toggles Sprint Visual)
#### Gamma
> - **Gamma** (toggles Gamma)

### PVP MODE
The **PVP MODE** sets *various* Minecraft options to the best values for pvp like turning off *unimportant* and *annoying* features which are unnecessary for PVP, toggle able once (can't be undone) with **/pvp**.

> - **No bob** (no camera shake while walking)
> - **No vignette** (no black borders)
> - **No damage tilt** (when you get damage your camera is not shaking)
> - **No narratorHotkey** (disables the narrator hot key)
> - **No fov effect** (this makes that if you accelerate your fov is not changing)
> - **No entity shadows** (disables the entity shadows, what can cause performance impacts)

### Timer
The Timer adds a stopwatch which is manageable and movable via GUI or the List of commands below.

> - **/timer toggle** toggles timer
> - **/timer start** starts timer
> - **/timer stop** stops timer
> - **/timer restart** restarts the timer
> - **/timer togglemode** switches between analog and digital timer mode

## Freecam
This Feature adds a Freecam to fly around like in Creative Mode while the Player does not move. The Freecam is movable by W, A, S, D, Left Shift and Space. These Key Binds can be changed in the Settings.
>- **/freecam toggle** toggles freecam
>- **/freecam speed** controls the speed of the Freecam
>- **F4** toggles Freecam

### Highlighting other Players with Eclipse Mod
This feature highlights Players with Eclipse Mod with an EP (and with different Ranks --> upcoming) before name.


In case of you having any brilliant ideas, please submit them here:
https://docs.google.com/forms/d/e/1FAIpQLSdC_3SM02lKfVTV1IyXYU7uZB4V9yEyLHUcJbVV_DdIoP3lsA/viewform?usp=publish-editor

## Basic UPDATE 1.0.0
- [x] Auto Sprinting
- [x] Fps counter
- [x] Resetrenderengine

## PVP UPDATE 1.0.1
- [x] PVP Mode

## Beta Freecam UPDATE 1.0.2
- [x] Freecam Basics
- [x] Freecam Up Move
- [x] Freecam Down Move
- [x] Resetrenderengine rewrote

## Freecam Movement UPDATE 1.0.3
- [x] Freecam Forward
- [x] Freecam Backward

## Timer UPDATE 1.0.4
- [x] Timer added
- [x] Timer  controls
- [x] Timer analog and digital

## Final Freecam UPDATE 1.0.5.2
- [x] Freecam Left
- [x] Freecam Right

## Bug Fixer UPDATE 1.0.6
- [x] Bug Fixes in if statement
- [x] Shorted code

## Version UPDATE 1.0.7
- [x] Code converted

## Version UPDATE 1.0.7.1
- [x] Freecam Fix
- [x] Removed VSync in PVPMODE

## Controls UPDATE 1.0.8
- [x] Control rebinding

## Save Data UPDATE 1.0.9
- [x] Implemented storage
- [x] Freecamspeed control
- [x] Color control

## Unhack UPDATE 1.1.0
- [x] Added unabletohack function

## Rename UPDATE 1.1.1
- [x] Renamed config file

## Back To The Past UPDATE 1.1.2
- [x] Brought all new features in 1.21.11

## Better UPDATE 1.1.3
- [x] Better code
- [x] Better timer logic
- [x] Config gui
- [x] Freecam hand fix
- [x] Less crashes
- [x] Timer & Fps hud portable
- [x] Threw aways: reload, colortimer, colorfps
- [x] Save Config fix
- [x] Better readable code
- [x] Better autosprint
- [x] Better freecam speed control

## Better UPDATE 1.1.3 (for older Version)
- [x] Better code
- [x] Better timer logic
- [x] Config gui
- [x] Freecam hand fix
- [x] Less crashes
- [x] Timer & Fps hud portable
- [x] Threw aways: reload, colortimer, colorfps
- [x] Save Config fix
- [x] Better readable code
- [x] Better autosprint
- [x] Better freecam speed control

## Better UPDATE 1.1.4
- [x] No Blur in move mode
- [x] Made only 1 move mode
- [x] Added grid to moving
- [x] Removed losing Focus while dragging
- [x] Added autosprint to HUD

## Better UPDATE 1.1.4 (for older Version)
- [x] No Blur in move mode
- [x] Made only 1 move mode
- [x] Added grid to moving
- [x] Removed losing Focus while dragging
- [x] Added autosprint to HUD

## Mixin Better UPDATE 1.1.5
- [x] No Server-Side Freecam Check
- [x] No fog
- [x] Optimized code
- [x] Better Freecam Performence

## Mixin Freecam But Better UPDATE 1.1.6
- [x] You can now see yourself in Freecam
- [x] Freecam Movement was made much better

## Big Step UPDATE 1.1.7
- [x] Now you can see players with my mod with an EP before name
- [x] Fog fix

## Big Step (AGAIN) UPDATE 1.1.8
- [x] Added multi Prefix Support
- [x] Better support at autosprint

## Mixed UPDATE 1.1.9
- [x] Better Prefix
- [x] Prefix now visible when playerlist is shown
- [x] Config GUI was optimized

## Featuring UPDATE 1.2.0
- [x] CPS Display added
- [x] Freecam Key Binds now configurable
- [x] Better Performance

## Big UPDATE 1.2.1
- [x] Better Performance
- [x] Eclipse Logo in Main menu
- [x] Eclipse in the name of the Minecraft window
- [x] Better Keybinds
- [x] CPS Display now toggleable
- [x] Ping Display added
- [x] Config Menu improvements
- [x] Toasts have been removed

## Upcoming
- [ ] Different Ranks for Markers before name

Hotfixes are not listed in the List because they mostly just solve bugs or make only small improvements.


**The GitHub repository will be on a newer status then Modrinth's page**

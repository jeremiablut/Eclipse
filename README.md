# **Eclipse**
This _Clientside_ Mod is meant as a collection of client-side utilities. This mod is still work in progress.
## Features:
>- **/toggle.fps** shows fps count in actionbar (defaults to false)
>- **/toggle.sprint** toggles sprinting (defaults to true)
>- **/reload** reloads the Recourcepacks
>- **/enable.pvp** enables pvp mode, more info below (defaults to true)
>- **/freecam** toggles freecam, more info below (defaults to false)
>- **/timer + expansion** more Info below
>- **/freecamspeed** + float
>- **/fpscolor** + int

### PVP MODE
the **PVP MODE** sets different Minecraft options to the best values for pvp.
```
Minecraft.getInstance().options.bobView().set(false);
Minecraft.getInstance().options.vignette().set(false);
Minecraft.getInstance().options.damageTiltStrength().set(0d);
Minecraft.getInstance().options.narratorHotkey().set(false);
Minecraft.getInstance().options.fovEffectScale().set(0d);
Minecraft.getInstance().options.entityShadows().set(false);
Minecraft.getInstance().options.save();
```

for those who don't know what that means, here is a list:
> - no bob (no camerashake while walking)
> - no vignette (no black borders)
> - no damagetilt (when you get damage your camera is not shaking)
> - no narratorHotkey (disables the narrator hot key)
> - no fov effect (this makes that if you accelerate your fov is not changing)
> - no entity shadows (disables the entity shadows, what can cause performance impacts)

### FREECAM
the **FREECAM** is still in development. (How it works below)

> 1. Summons an InteractionEntity
> 2. Sets the PlayerCamera in the InteractionEntity
> 3. Every tick the rot of the InteractionEntity is synced with the player rot

### Timer
The timer adds a stopwatch (*brillant*) in the actionbar.
> - **/timer.toggle** toggles timer
> - **/timer.start** starts timer
> - **/timer.stop** stops timer
> - **/timer.restart** restarts the timer
> - **/timer.toggleMode** switches between analog and digital timer mode
> - **/timercolor** sets the color

**WARNING:** Client World will crash if you punch an entity while in freecam

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
- [x] shorted code

## Version UPDATE 1.0.7
- [x] Code converted

## Version UPDATE 1.0.7.1
- [x] Freecam Fix
- [x] Removed VSync in PVPMODE

## Controls UPDATE 1.0.8
- [x] Control rebinding

**.  .  .**

## Modmenu UPDATE x.x.x
- [ ] Modmenu Configuration

In case of you having any brilliant ideas, please submit them here:
https://docs.google.com/forms/d/e/1FAIpQLSdC_3SM02lKfVTV1IyXYU7uZB4V9yEyLHUcJbVV_DdIoP3lsA/viewform?usp=publish-editor

**The GitHub repository will be on a newer status then Modrinth's page**
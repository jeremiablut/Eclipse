# **Eclipse**
This _Clientside_ Mod is meant as a collection of client-side utilities. This mod is still work in progress.
## Features:
>- **/toggle.fps** shows fps count in actionbar (defaults to false)
>- **/toggle.sprint** toggles sprinting (defaults to true)
>- **/reload** reloads the Recourcepacks
>- **/enable.pvp** enables pvp mode, more info below (defaults to true)
>- **/freecam** toggles freecam, more info below (defaults to false)

### PVP MODE
the **PVP MODE** sets different Minecraft options to the best values for pvp.
```
Minecraft.getInstance().options.bobView().set(false);
Minecraft.getInstance().options.vignette().set(false);
Minecraft.getInstance().options.damageTiltStrength().set(0d);
Minecraft.getInstance().options.narratorHotkey().set(false);
Minecraft.getInstance().options.enableVsync().set(false);
Minecraft.getInstance().options.fovEffectScale().set(0d);
Minecraft.getInstance().options.entityShadows().set(false);
Minecraft.getInstance().options.save();
```

for those who don't know what that means, here is a list:
> - no bob (no camerashake while walking)
> - no vignette (no black borders)
> - no damagetilt (when you get damage your camera is not shaking)
> - no narratorHotkey (disables the narrator hot key)
> - no Vsync
> - no fov effect (this makes that if you accelerate your fov is not changing)
> - no entity shadows (disables the entity shadows, what can cause performance impacts)

### FREECAM
the **FREECAM** is still in development. (How it works below)

> 1. Summons an InteractionEntity
> 2. Sets the PlayerCamera in the InteractionEntity
> 3. Every tick the rot of the InteractionEntity is synced with the player rot

### CONTROLS:
> Freecam:
> > W - Forward
>
> > S - Backward 
>
> > SPACE - Up
>
> > Shift - Down

**WARNING:** Client World will crash if you punch in air while in freecam

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


- 
-
-
## Final Freecam UPDATE 1.x.x
- [ ] Freecam Left
- [ ] Freecam Right
- [ ] Configurable Keys

## Fullbright UPDATE x.x.x
- [ ] Fullbright

## Modmenu UPDATE x.x.x
- [ ] Modmenu Configuration

In case of you having any brilliant ideas, please submit them hear:
https://docs.google.com/forms/d/e/1FAIpQLSdC_3SM02lKfVTV1IyXYU7uZB4V9yEyLHUcJbVV_DdIoP3lsA/viewform?usp=publish-editor

**The GitHub repository will be on a newer status then Modrinth's page**
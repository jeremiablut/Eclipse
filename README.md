# **Eclipse**
This _Clientside_ Mod is meant as a collection of client-side utilities. This mod is still work in progress.
## Features:
>- **/toggle.fps** shows fps count in actionbar (default on false)
>- **/toggle.sprint** toggles sprinting (default on true)
>- **/renderreset** resets the Renderengine
>- **/enable.pvp** enables pvp mode, more info below (default on true)

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
> - no damagetilt (when u get damage your camera is not shaking)
> - no narratorHotkey (disables the narrator hot key)
> - no Vsync
> - no fov effect (this makes that if you accelerate your fov is not changing)
> - no entity shadows (disables the entity shadows, what can cause performance impacts)

**The GitHub repository will be on a newer status then Modrinth's page**

# **Eclipse**
This _Clientside_ Mod is meant as a library of all stuff i made. Because of Beta it has not that many feature which are listed bellow. This mod is still work in progress.
## Features:
>- **/toggle.fps** shows fps count in actionbar (default on false)
>- **/toggle.sprint** toggles sprinting (default on true)
>- **/crash** just crashes your game
>- **/renderreset** resets the Renderengine
>- **/toggle.pvp** toggles pvp mode, more info below (default on true)

### PVP MODE
the **PVP MODE** sets different Minecraft options to the best values for pvp.
>```
>minecraft.options.bobView().set(!pvp);
>minecraft.options.vignette().set(!pvp);
>minecraft.options.damageTiltStrength().set(0d);
>minecraft.options.narratorHotkey().set(!pvp);
>minecraft.options.enableVsync().set(!pvp);
>minecraft.options.fovEffectScale().set(0d);
>minecraft.options.entityShadows().set(!pvp);
>```
for those who don't know what that means, here is a list:
> - no bob (no camerashake while walking)
> - no vignette (no black borders)
> - no damagetilt (when u get damage your camera is not shaking)
> - no narratorHotkey (disables the narrator hot key)
> - no Vsync
> - no fov effect (this makes that if u accelerate your fov is not changing)
> - no entity shadows (disables the entity shadows, what can cause performence impacts)

**The GitHub repository will be on a newer status then Modrinth's page**

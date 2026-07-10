package com.eclipse.client.config;

import com.eclipse.client.enums.DayTime;
import com.eclipse.client.enums.TimerAction;
import com.eclipse.client.Vec2I;

public class ModConfig {
    public boolean witfit = true, fps = false, nofog = true, sprint = true, cacheSprint, shown = false, digital = true, sprintVisual = true, gamma = true, cps = true, pingSelf = true, pingOthers = true, nopumpkin = true, server = false, armor = true;
    public float distance = 1f;
    public String timer;
    public int ticks = 0, seconds, minutes = 0, hours = 0;
    public Vec2I fpsV = new Vec2I(10, 10), timerV = new Vec2I(10, 20), sprintV = new Vec2I(10, 30), cpsV = new Vec2I(10, 40), pingV = new Vec2I(10, 50), serverV = new Vec2I(10, 60), armorV = new Vec2I(10, 70),
    witfitV = new Vec2I(10, 120);
    public TimerAction timerAction = TimerAction.PLAY;
    public DayTime dayTime = DayTime.OFF;
    public ConfigValue pingPrefix = new ConfigValue("", ""), pingSuffix = new ConfigValue("ms", "ms"), sprinting = new ConfigValue("SPRINTING", "SPRINTING"), walking = new ConfigValue("WALKING", "WALKING"),
    fpsPrefix = new ConfigValue("FPS: ", "FPS: "), fpsSuffix = new ConfigValue("", "");
}
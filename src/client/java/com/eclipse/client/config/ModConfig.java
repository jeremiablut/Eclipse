package com.eclipse.client.config;

import com.eclipse.client.Vec2I;

public class ModConfig {
    public boolean fps = false, nofog = true, sprint = true, cacheSprint, shown = false, digital = true, autoSprint = true, sprintVisual = true, gamma = true, cps = true, pingSelf = true, pingOthers = true;
    public float distance = 1f;
    public String status, timer, name;
    public int ticks = 0, seconds, minutes = 0, hours = 0;
    public Vec2I fpsV = new Vec2I(10, 10), timerV = new Vec2I(10, 20), sprintV = new Vec2I(10, 30), cpsV = new Vec2I(10, 40), pingV = new Vec2I(10, 50);
}
package com.eclipse.client;

import com.eclipse.client.config.ConfigManager;
import com.eclipse.client.config.ModConfig;
import com.eclipse.client.enums.TimerAction;

public class TimerController {
    private static ModConfig config;
    private static boolean initialized = false;

    public TimerController(ModConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("ERROR CONFIG");
        }
        TimerController.config = config;
        initialized = true;
    }

    public void controlTimer(TimerAction action) {
        if (!initialized) return;

        config.timerAction = action;

        switch (action) {
            case RESTART -> restart();
            case PLAY, STOPPED -> {}
        }

        formatTimer();
        ConfigManager.save();
    }

    // RESTART TIMER
    public void restart() {
        config.ticks = 0;
        config.seconds = 0;
        config.minutes = 0;
        config.hours = 0;
    }

    // TICK
    public void tick() {
        if (!initialized) return;

        int oldSeconds = config.seconds;

        if (config.timerAction == TimerAction.PLAY) {
            config.ticks++;
        }

        config.seconds = config.ticks / 20;

        if (config.seconds != oldSeconds) {
            formatTimer();
        }

        if (config.seconds >= 60) {
            config.ticks = 0;
            if (config.minutes < 60) {
                config.minutes++;
            } else {
                config.minutes = 0;
                config.hours++;
            }
            formatTimer();
            ConfigManager.save();
        }
    }

    // TOGGLE TIMER VISIBILITY
    public void toggleTimer() {
        if (!initialized) return;
        config.shown = !config.shown;
        ConfigManager.save();
    }

    // FORMAT TIMER
    public void formatTimer() {
        if (!initialized) return;

        if (config.digital) {
            if (config.hours > 0) {
                config.timer = String.format("%d:%02d:%02d", config.hours, config.minutes, config.seconds);
            } else if (config.minutes > 0) {
                config.timer = String.format("%d:%02d", config.minutes, config.seconds);
            } else {
                config.timer = String.valueOf(config.seconds);
            }
        } else {
            if (config.hours > 0) {
                config.timer = String.format("%dh %02dmin %02ds", config.hours, config.minutes, config.seconds);
            } else if (config.minutes > 0) {
                config.timer = String.format("%dmin %02ds", config.minutes, config.seconds);
            } else {
                config.timer = config.seconds + "s";
            }
        }
    }
}
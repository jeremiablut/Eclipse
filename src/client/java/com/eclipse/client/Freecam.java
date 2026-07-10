package com.eclipse.client;

import com.eclipse.client.config.ConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.network.chat.Component;

import static com.eclipse.client.EclipseClient.freecam;
import static com.eclipse.client.EclipseClient.freecamEntity;
import static com.eclipse.client.config.ConfigManager.config;

public class Freecam {

    private static final Minecraft mc = Minecraft.getInstance();
    private static float xRot, yRot;

    // ENABLE FREECAM
    public static void enable() {
        if (mc.player == null || mc.level == null) return;

        xRot = mc.player.getXRot();
        yRot = mc.player.getYRot();

        if (freecam) return;

        freecamEntity = new FreecamEntity(mc.level);
        freecamEntity.setPos(mc.player.getX(), mc.player.getY() + 2, mc.player.getZ());

        mc.level.addEntity(freecamEntity);
        mc.setCameraEntity(freecamEntity);

        config.cacheSprint = config.sprint;
        config.sprint = false;

        ConfigManager.save();

        freecam = true;
    }

    // DISABLE FREECAM
    public static void disable() {
        if (!freecam) return;

        config.sprint = config.cacheSprint;
        ConfigManager.save();

        mc.setCameraEntity(mc.player);

        mc.player.setXRot(xRot);
        mc.player.setYRot(yRot);

        if (freecamEntity != null) {
            freecamEntity.discard();
            freecamEntity = null;
        }

        freecam = false;
    }

    // TOGGLE FREECAM
    public static void toggle() {
        if (freecam) {
            disable();
        } else {
            enable();
        }
    }
}

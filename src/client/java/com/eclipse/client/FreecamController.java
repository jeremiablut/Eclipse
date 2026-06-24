package com.eclipse.client;

import com.eclipse.client.config.ModConfig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.glfw.GLFW;

import static com.eclipse.client.EclipseClient.freecamEntity;

public class FreecamController {
    private EasyMapping wFreecam, aFreecam, sFreecam, dFreecam, upFreecam, downFreecam;
    private ModConfig config;
    private static final double SMOOTHING = 0.2;
    public void init(ModConfig config) {
        this.config = config;

        wFreecam = new EasyMapping("Freecam Forward", GLFW.GLFW_KEY_W);

        aFreecam = new EasyMapping("Freecam Left", GLFW.GLFW_KEY_A);

        sFreecam = new EasyMapping("Freecam Backward", GLFW.GLFW_KEY_S);

        dFreecam = new EasyMapping("Freecam Right", GLFW.GLFW_KEY_D);

        upFreecam = new EasyMapping("Freecam Up", GLFW.GLFW_KEY_SPACE);

        downFreecam = new EasyMapping("Freecam Down", GLFW.GLFW_KEY_LEFT_SHIFT);
    }

    public void tick(Player player) {
        if (!EclipseClient.freecam || EclipseClient.freecamEntity == null) return;
        Vec3 forward = new Vec3(
                -Math.sin(Math.toRadians(player.getYRot())),
                0,
                Math.cos(Math.toRadians(player.getYRot()))
        ).normalize();

        Vec3 right = new Vec3(
                -forward.z,
                0,
                forward.x
        ).normalize();



        double x = freecamEntity.getX();
        double y = freecamEntity.getY();
        double z = freecamEntity.getZ();

        Vec3 motion = Vec3.ZERO;

        if (wFreecam.isDown()) {
            motion = motion.add(forward);
        }

        if (aFreecam.isDown()) {
            motion = motion.subtract(right);
        }

        if (sFreecam.isDown()) {
            motion = motion.subtract(forward);
        }

        if (dFreecam.isDown()) {
            motion = motion.add(right);
        }

        // vertical extra
        if (upFreecam.isDown()) {
            motion = motion.add(0, 1, 0);
        }
        if (downFreecam.isDown()) {
            motion = motion.add(0, -1, 0);
        }

        Vec3 current = freecamEntity.getDeltaMovement();
        Vec3 target = motion.length() > 0
                ? motion.normalize().scale(config.distance)
                : Vec3.ZERO;

        Vec3 smoothed = current.add(target.subtract(current).scale(SMOOTHING));

        freecamEntity.setDeltaMovement(smoothed);


        // POS SYNC
        freecamEntity.setPos(x, y, z);
    }
}

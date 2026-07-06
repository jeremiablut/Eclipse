package com.eclipse.client.ConfigScreen;

import com.eclipse.client.EclipseClient;
import com.eclipse.client.config.ConfigManager;
import com.eclipse.client.ui.EclipseButton;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

import static com.eclipse.client.ConfigScreen.CustomScreen.buttonHeight;
import static com.eclipse.client.ConfigScreen.CustomScreen.buttonWidth;
import static com.eclipse.client.EclipseClient.*;

public class Drager extends Screen {
    public Drager(Component title) {
        super(title);
    }
    private int background;
    private Window window = Minecraft.getInstance().getWindow();

    @Override
    public void init() {
        background = Minecraft.getInstance().options.menuBackgroundBlurriness().get();
        Minecraft.getInstance().options.menuBackgroundBlurriness().set(0);
        int x = this.width / 2 - buttonWidth / 2;

        this.addRenderableWidget(
                new EclipseButton(
                        x, 40, buttonWidth, buttonHeight,
                        Component.literal("RESET"),
                        (btn) -> {
                            EclipseClient.resetPos();
                        }
                )
        );
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().options.menuBackgroundBlurriness().set(background);
        minecraft.setScreen(
                new CustomScreen(Component.empty())
        );
    }

    @Override
    public void tick() {
        if (GLFW.glfwGetMouseButton(window.handle(), 0) == GLFW.GLFW_PRESS) {
            config.fpsV = fpsWidget.pos;
            fpsWidget.refresh();

            config.timerV = timerWidget.pos;
            timerWidget.refresh();

            config.sprintV = sprintWidget.pos;
            sprintWidget.refresh();

            config.cpsV = cpsWidget.pos;
            cpsWidget.refresh();

            config.pingV = pingWidget.pos;
            pingWidget.refresh();
            ConfigManager.save();
        } else {
            selected = 0;
        }
    }
}

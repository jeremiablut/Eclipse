package com.eclipse.client.ConfigScreen.FPSConfig;

import com.eclipse.client.ConfigScreen.CustomScreen;
import com.eclipse.client.EclipseClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class FpsConfig extends Screen {
    public FpsConfig(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int buttonWidth = 120;
        int buttonHeight = 20;
        int x = this.width / 2 - buttonWidth / 2;

        this.addRenderableWidget(
                Button.builder(Component.literal("Toggle FPS"), (btn) -> {
                    EclipseClient.toggleFPS();
                }).bounds(x, 40, buttonWidth, buttonHeight).build()
        );
    }

    @Override
    public void onClose() {
        minecraft.setScreen(
                new CustomScreen(Component.empty())
        );
    }
}

package com.eclipse.client.ConfigScreen;

import com.eclipse.client.ConfigScreen.FPSConfig.FpsConfig;
import com.eclipse.client.ConfigScreen.TimerConfig.TimerConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class CustomScreen extends Screen {
    public CustomScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int buttonWidth = 120;
        int buttonHeight = 20;
        int x = this.width / 2 - buttonWidth / 2;

        this.addRenderableWidget(
                Button.builder(Component.literal("Config FPS"), (btn) -> {
                    Minecraft.getInstance().setScreen(new FpsConfig(Component.empty()));
                }).bounds(x, 40, buttonWidth, buttonHeight).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal("Config Timer"), (btn) -> {
                    Minecraft.getInstance().setScreen(new TimerConfig(Component.empty()));
                }).bounds(x, 80, buttonWidth, buttonHeight).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal("MOVE WIDGETS"), (btn) -> {
                    Minecraft.getInstance().setScreen(new Drager(Component.empty()));
                }).bounds(x, 120, buttonWidth, buttonHeight).build()
        );
    }
}

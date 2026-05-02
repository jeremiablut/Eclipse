package com.eclipse.client.ConfigScreen.TimerConfig;

import com.eclipse.client.ConfigScreen.CustomScreen;
import com.eclipse.client.EclipseClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class TimerConfig extends Screen {
    public TimerConfig(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int buttonWidth = 120;
        int buttonHeight = 20;
        int x = this.width / 2 - buttonWidth / 2;

        this.addRenderableWidget(
                Button.builder(Component.literal("Toggle Timer"), (btn) -> {
                    EclipseClient.toggleTimer();
                }).bounds(x, 40, buttonWidth, buttonHeight).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal("Start Timer"), (btn) -> {
                    EclipseClient.controlTimer("started");
                }).bounds(x, 80, buttonWidth, buttonHeight).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal("Restart Timer"), (btn) -> {
                    EclipseClient.timerRestart();
                }).bounds(x, 160, buttonWidth, buttonHeight).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal("Stop Timer"), (btn) -> {
                    EclipseClient.controlTimer("stopped");
                }).bounds(x, 120, buttonWidth, buttonHeight).build()
        );
    }

    @Override
    public void onClose() {
        minecraft.setScreen(
                new CustomScreen(Component.empty())
        );
    }
}

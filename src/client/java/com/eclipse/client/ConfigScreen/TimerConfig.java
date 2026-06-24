package com.eclipse.client.ConfigScreen;

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
        int x = this.width / 5 * 2;

        String call = "Timer: ";

        this.addRenderableWidget(
                Button.builder(Component.literal("«"), (btn) -> {
                    Minecraft.getInstance().setScreen(new CustomScreen(Component.empty()));
                }).bounds(0, 40, 40, 20).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal(call + EclipseClient.getTimer()), (btn) -> {
                    EclipseClient.setTimer(!EclipseClient.getTimer());
                    btn.setMessage(Component.nullToEmpty(call + EclipseClient.getTimer()));
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

package com.eclipse.client.ConfigScreen;

import com.eclipse.client.EclipseClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class Drager extends Screen {
    public Drager(Component title) {
        super(title);
    }
    private int background;

    @Override
    public void init() {
        background = Minecraft.getInstance().options.menuBackgroundBlurriness().get();
        Minecraft.getInstance().options.menuBackgroundBlurriness().set(0);

        int buttonWidth = 20;
        int buttonHeight = 20;
        int x = this.width / 2 - buttonWidth / 2;

        this.addRenderableWidget(
                Button.builder(Component.literal("⟳"), (btn) -> {
                    EclipseClient.sprintReset();
                    EclipseClient.fpsReset();
                    EclipseClient.timerReset();
                }).bounds(x, 40, buttonWidth, buttonHeight).build()
        );
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().options.menuBackgroundBlurriness().set(background);
        minecraft.setScreen(
                new CustomScreen(Component.empty())
        );
    }
}

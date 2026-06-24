package com.eclipse.client.ConfigScreen;

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
        int x = this.width / 5 * 2;

        String call = "FPS: ";

        this.addRenderableWidget(
                Button.builder(Component.literal(call + EclipseClient.getFPS()), (btn) -> {
                    EclipseClient.setFPS(!EclipseClient.getFPS());
                    btn.setMessage(Component.nullToEmpty(call + EclipseClient.getFPS()));
                }).bounds(x, 40, buttonWidth, buttonHeight).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal("«"), (btn) -> {
                    Minecraft.getInstance().setScreen(new CustomScreen(Component.empty()));
                }).bounds(0, 40, 40, 20).build()
        );
    }

    @Override
    public void onClose() {
        minecraft.setScreen(
                new CustomScreen(Component.empty())
        );
    }
}

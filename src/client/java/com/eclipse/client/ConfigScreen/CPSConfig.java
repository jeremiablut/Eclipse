package com.eclipse.client.ConfigScreen;

import com.eclipse.client.EclipseClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class CPSConfig extends Screen {
    public CPSConfig(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int buttonWidth = 120;
        int buttonHeight = 20;
        int x = this.width / 5 * 2;

        String call = "CPS: ";

        this.addRenderableWidget(
                Button.builder(Component.literal("«"), (btn) -> {
                    Minecraft.getInstance().setScreen(new CustomScreen(Component.empty()));
                }).bounds(0, 40, 40, 20).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal(call + EclipseClient.getCPS()), (btn) -> {
                    EclipseClient.setCPS(!EclipseClient.getCPS());
                    btn.setMessage(Component.nullToEmpty(call + EclipseClient.getCPS()));
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


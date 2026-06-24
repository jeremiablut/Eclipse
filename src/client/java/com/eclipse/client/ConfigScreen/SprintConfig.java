package com.eclipse.client.ConfigScreen;

import com.eclipse.client.EclipseClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SprintConfig extends Screen {
    public SprintConfig(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int buttonWidth = 120;
        int buttonHeight = 20;
        int x = this.width / 5 * 2;

        String call = "Auto Sprint: ";

        this.addRenderableWidget(
                Button.builder(Component.literal(call + EclipseClient.getSprint()), (btn) -> {
                    EclipseClient.setSprint(!EclipseClient.getSprint());
                    btn.setMessage(Component.nullToEmpty(call + EclipseClient.getSprint()));
                }).bounds(x, 40, buttonWidth, buttonHeight).build()
        );

        String call1 = "Visual Sprint: ";

        this.addRenderableWidget(
                Button.builder(Component.literal(call1 + EclipseClient.getSprintVisual()), (btn) -> {
                    EclipseClient.setSprintVisual(!EclipseClient.getSprintVisual());
                    btn.setMessage(Component.nullToEmpty(call1 + EclipseClient.getSprintVisual()));
                }).bounds(x, 80, buttonWidth, buttonHeight).build()
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


package com.eclipse.client.ConfigScreen;

import com.eclipse.client.EclipseClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class PingConfig extends Screen {
    public PingConfig(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int buttonWidth = 120;
        int buttonHeight = 20;
        int x = this.width / 5 * 2;

        String call1 = "Ping Self: ";
        String call2 = "Ping TAB: ";

        this.addRenderableWidget(
                Button.builder(Component.literal(call1 + EclipseClient.getPingSelf()), (btn) -> {
                    EclipseClient.setPingSelf(!EclipseClient.getPingSelf());
                    btn.setMessage(Component.nullToEmpty(call1 + EclipseClient.getPingSelf()));
                }).bounds(x, 40, buttonWidth, buttonHeight).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal("«"), (btn) -> {
                    Minecraft.getInstance().setScreen(new CustomScreen(Component.empty()));
                }).bounds(0, 40, 40, 20).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal(call2 + EclipseClient.getPingOthers()), (btn) -> {
                    EclipseClient.setPingOthers(!EclipseClient.getPingOthers());
                    btn.setMessage(Component.nullToEmpty(call2 + EclipseClient.getPingOthers()));
                }).bounds(x, 80, buttonWidth, buttonHeight).build()
        );
    }

    @Override
    public void onClose() {
        minecraft.setScreen(
                new CustomScreen(Component.empty())
        );
    }
}

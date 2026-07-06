package com.eclipse.client.ConfigScreen;

import com.eclipse.client.EclipseClient;
import com.eclipse.client.ui.EclipseButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static com.eclipse.client.ConfigScreen.CustomScreen.buttonHeight;
import static com.eclipse.client.ConfigScreen.CustomScreen.buttonWidth;

public class PingConfig extends Screen {
    public PingConfig(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int x = this.width / 2- buttonWidth / 2;

        String call1 = "Ping Self: ";
        String call2 = "Ping TAB: ";

        this.addRenderableWidget(
                new EclipseButton(
                        x, 40, buttonWidth, buttonHeight,
                        Component.literal(call1 + EclipseClient.getPingSelf()),
                        (btn) -> {
                            EclipseClient.setPingSelf(!EclipseClient.getPingSelf());
                            btn.setMessage(Component.nullToEmpty(call1 + EclipseClient.getPingSelf()));
                        }
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        0, 40, 40, 20,
                        Component.literal("«"),
                        (btn) -> Minecraft.getInstance().setScreen(new CustomScreen(Component.empty()))
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        x, 90, buttonWidth, buttonHeight,
                        Component.literal(call2 + EclipseClient.getPingOthers()),
                        (btn) -> {
                            EclipseClient.setPingOthers(!EclipseClient.getPingOthers());
                            btn.setMessage(Component.nullToEmpty(call2 + EclipseClient.getPingOthers()));
                        }
                )
        );
    }

    @Override
    public void onClose() {
        minecraft.setScreen(
                new CustomScreen(Component.empty())
        );
    }
}

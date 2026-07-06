package com.eclipse.client.ConfigScreen;

import com.eclipse.client.EclipseClient;
import com.eclipse.client.ui.EclipseButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static com.eclipse.client.ConfigScreen.CustomScreen.buttonHeight;
import static com.eclipse.client.ConfigScreen.CustomScreen.buttonWidth;

public class FpsConfig extends Screen {
    public FpsConfig(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int x = this.width / 2- buttonWidth / 2;

        String call = "FPS: ";

        this.addRenderableWidget(
                new EclipseButton(
                        x, 40, buttonWidth, buttonHeight,
                        Component.literal(call + EclipseClient.getFPS()),
                        (btn) -> {
                            EclipseClient.setFPS(!EclipseClient.getFPS());
                            btn.setMessage(Component.nullToEmpty(call + EclipseClient.getFPS()));
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
    }

    @Override
    public void onClose() {
        minecraft.setScreen(
                new CustomScreen(Component.empty())
        );
    }
}

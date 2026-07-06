package com.eclipse.client.ConfigScreen;

import com.eclipse.client.EclipseClient;
import com.eclipse.client.ui.EclipseButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static com.eclipse.client.ConfigScreen.CustomScreen.buttonHeight;
import static com.eclipse.client.ConfigScreen.CustomScreen.buttonWidth;

public class SprintConfig extends Screen {
    public SprintConfig(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int x = this.width / 2- buttonWidth / 2;

        String call = "Auto Sprint: ";

        this.addRenderableWidget(
                new EclipseButton(
                        x, 40, buttonWidth, buttonHeight,
                        Component.literal(call + EclipseClient.getSprint()),
                        (btn) -> {
                            EclipseClient.setSprint(!EclipseClient.getSprint());
                            btn.setMessage(Component.nullToEmpty(call + EclipseClient.getSprint()));
                        }
                )
        );

        String call1 = "Visual Sprint: ";

        this.addRenderableWidget(
                new EclipseButton(
                        x, 90, buttonWidth, buttonHeight,
                        Component.literal(call1 + EclipseClient.getSprintVisual()),
                        (btn) -> {
                            EclipseClient.setSprintVisual(!EclipseClient.getSprintVisual());
                            btn.setMessage(Component.nullToEmpty(call1 + EclipseClient.getSprintVisual()));
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


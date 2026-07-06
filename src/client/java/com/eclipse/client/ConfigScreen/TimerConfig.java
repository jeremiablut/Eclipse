package com.eclipse.client.ConfigScreen;

import com.eclipse.client.EclipseClient;
import com.eclipse.client.ui.EclipseButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static com.eclipse.client.ConfigScreen.CustomScreen.buttonHeight;
import static com.eclipse.client.ConfigScreen.CustomScreen.buttonWidth;

public class TimerConfig extends Screen {
    public TimerConfig(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int x = this.width / 2- buttonWidth / 2;

        String call = "Timer: ";

        this.addRenderableWidget(
                new EclipseButton(
                        0, 40, 40, 20,
                        Component.literal("«"),
                        (btn) -> Minecraft.getInstance().setScreen(new CustomScreen(Component.empty()))
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        x, 40, buttonWidth, buttonHeight,
                        Component.literal(call + EclipseClient.getTimer()),
                        (btn) -> {
                            EclipseClient.setTimer(!EclipseClient.getTimer());
                            btn.setMessage(Component.nullToEmpty(call + EclipseClient.getTimer()));
                        }
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        x, 90, buttonWidth, buttonHeight,
                        Component.literal("Start Timer"),
                        (btn) -> {
                            EclipseClient.controlTimer("started");
                        }
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        x, 140, buttonWidth, buttonHeight,
                        Component.literal("Reset Timer"),
                        (btn) -> {
                            EclipseClient.timerRestart();
                        }
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        x, 190, buttonWidth, buttonHeight,
                        Component.literal("Stop Timer"),
                        (btn) -> {
                            EclipseClient.controlTimer("stopped");;
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

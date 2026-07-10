package com.eclipse.client.ConfigScreen;

import com.eclipse.client.EclipseClient;
import com.eclipse.client.enums.TimerAction;
import com.eclipse.client.ui.EclipseButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static com.eclipse.client.ConfigScreen.CustomScreen.buttonHeight;
import static com.eclipse.client.ConfigScreen.CustomScreen.buttonWidth;
import static com.eclipse.client.EclipseClient.timerController;

public class TimerConfig extends Screen {
    public TimerConfig(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int x = this.width / 2- buttonWidth / 2;

        String call = "Timer";

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
                        Component.literal(call).withColor(EclipseClient.config.shown ? 0x00c800 : 0xc80700),
                        (btn) -> {
                            EclipseClient.config.shown = !EclipseClient.config.shown;
                            btn.setMessage(Component.literal(call).withColor(EclipseClient.config.shown ? 0x00c800 : 0xc80700));
                        }
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        x, 90, buttonWidth, buttonHeight,
                        Component.literal("Start"),
                        (btn) -> {
                            timerController.controlTimer(TimerAction.PLAY);
                        }
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        x, 140, buttonWidth, buttonHeight,
                        Component.literal("Restart"),
                        (btn) -> {
                            timerController.controlTimer(TimerAction.RESTART);
                        }
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        x, 190, buttonWidth, buttonHeight,
                        Component.literal("Stop"),
                        (btn) -> {
                            timerController.controlTimer(TimerAction.STOPPED);
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

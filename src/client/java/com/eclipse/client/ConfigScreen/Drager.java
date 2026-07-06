package com.eclipse.client.ConfigScreen;

import com.eclipse.client.EclipseClient;
import com.eclipse.client.ui.EclipseButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static com.eclipse.client.ConfigScreen.CustomScreen.buttonHeight;
import static com.eclipse.client.ConfigScreen.CustomScreen.buttonWidth;

public class Drager extends Screen {
    public Drager(Component title) {
        super(title);
    }
    private int background;

    @Override
    public void init() {
        background = Minecraft.getInstance().options.menuBackgroundBlurriness().get();
        Minecraft.getInstance().options.menuBackgroundBlurriness().set(0);
        int x = this.width / 2 - buttonWidth / 2;

        this.addRenderableWidget(
                new EclipseButton(
                        x, 40, buttonWidth, buttonHeight,
                        Component.literal("RESET"),
                        (btn) -> {
                            EclipseClient.resetPos();
                        }
                )
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

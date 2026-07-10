package com.eclipse.client.ConfigScreen;

import com.eclipse.client.EclipseClient;
import com.eclipse.client.config.ConfigManager;
import com.eclipse.client.ui.EclipseButton;
import com.eclipse.client.ui.EclipseRefreshButton;
import com.eclipse.client.ui.EclipseTextArea;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static com.eclipse.client.ConfigScreen.CustomScreen.buttonHeight;
import static com.eclipse.client.ConfigScreen.CustomScreen.buttonWidth;

public class SprintConfig extends Screen {
    public SprintConfig(Component title) {
        super(title);
    }
    public EclipseTextArea sprinting, walking;

    @Override
    protected void init() {
        int x = this.width / 2- buttonWidth / 2;

        String call = "Auto Sprint";

        this.addRenderableWidget(
                new EclipseButton(
                        x, 40, buttonWidth, buttonHeight,
                        Component.literal(call).withColor(EclipseClient.config.sprint ? 0x00c800 : 0xc80700),
                        (btn) -> {
                            EclipseClient.config.sprint = !EclipseClient.config.sprint;
                            btn.setMessage(Component.literal(call).withColor(EclipseClient.config.sprint ? 0x00c800 : 0xc80700));
                        }
                )
        );

        String call1 = "Visible";

        this.addRenderableWidget(
                new EclipseButton(
                        x, 90, buttonWidth, buttonHeight,
                        Component.literal(call1).withColor(EclipseClient.config.sprintVisual ? 0x00c800 : 0xc80700),
                        (btn) -> {
                            EclipseClient.config.sprintVisual = !EclipseClient.config.sprintVisual;
                            btn.setMessage(Component.literal(call1).withColor(EclipseClient.config.sprintVisual ? 0x00c800 : 0xc80700));
                        }
                )
        );

        sprinting = new EclipseTextArea(Minecraft.getInstance().font, x, 140, buttonWidth, 15, "Sprint text");
        sprinting.setValue(EclipseClient.config.sprinting.custom.toString());
        this.addRenderableWidget(sprinting);
        this.addRenderableWidget(new EclipseRefreshButton(sprinting, (btn) -> EclipseClient.config.sprinting.reset(sprinting)));

        walking = new EclipseTextArea(Minecraft.getInstance().font, x, 175, buttonWidth, 15, "Walking text");
        walking.setValue(EclipseClient.config.walking.custom.toString());
        this.addRenderableWidget(walking);
        this.addRenderableWidget(new EclipseRefreshButton(walking, (btn) -> EclipseClient.config.walking.reset(walking)));

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

        ConfigManager.save();
    }

    public void tick() {
        EclipseClient.config.sprinting.custom = sprinting.getValue();
        EclipseClient.config.walking.custom = walking.getValue();
    }
}


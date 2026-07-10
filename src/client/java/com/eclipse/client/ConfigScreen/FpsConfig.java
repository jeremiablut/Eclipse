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

public class FpsConfig extends Screen {
    public FpsConfig(Component title) {
        super(title);
    }
    public EclipseTextArea fpsPrefix, fpsSuffix;

    @Override
    protected void init() {
        int x = this.width / 2- buttonWidth / 2;

        String call = "FPS";

        this.addRenderableWidget(
                new EclipseButton(
                        x, 40, buttonWidth, buttonHeight,
                        Component.literal(call).withColor(EclipseClient.config.fps ? 0x00c800 : 0xc80700),
                        (btn) -> {
                            EclipseClient.config.fps = !EclipseClient.config.fps;
                            btn.setMessage(Component.literal(call).withColor(EclipseClient.config.fps ? 0x00c800 : 0xc80700));
                        }
                )
        );

        fpsPrefix = new EclipseTextArea(Minecraft.getInstance().font, x, 90, buttonWidth, 15, "Prefix");
        fpsPrefix.setValue(EclipseClient.config.fpsPrefix.custom.toString());
        this.addRenderableWidget(fpsPrefix);
        this.addRenderableWidget(new EclipseRefreshButton(fpsPrefix, (btn) -> EclipseClient.config.fpsPrefix.reset(fpsPrefix)));

        fpsSuffix = new EclipseTextArea(Minecraft.getInstance().font, x, 125, buttonWidth, 15, "Suffix");
        fpsSuffix.setValue(EclipseClient.config.fpsSuffix.custom.toString());
        this.addRenderableWidget(fpsSuffix);
        this.addRenderableWidget(new EclipseRefreshButton(fpsSuffix, (btn) -> EclipseClient.config.fpsSuffix.reset(fpsSuffix)));

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
        EclipseClient.config.fpsPrefix.custom = fpsPrefix.getValue();
        EclipseClient.config.fpsSuffix.custom = fpsSuffix.getValue();
    }
}

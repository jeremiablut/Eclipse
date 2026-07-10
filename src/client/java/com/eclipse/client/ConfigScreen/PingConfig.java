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

public class PingConfig extends Screen {
    public PingConfig(Component title) {
        super(title);
    }
    public EclipseTextArea prefixTextArea, suffixTextArea;

    @Override
    protected void init() {
        int x = this.width / 2- buttonWidth / 2;

        String call1 = "Ping Self";
        String call2 = "Ping TAB";

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
                        Component.literal(call1).withColor(EclipseClient.config.pingSelf ? 0x00c800 : 0xc80700),
                        (btn) -> {
                            EclipseClient.config.pingSelf = !EclipseClient.config.pingSelf;
                            btn.setMessage(Component.literal(call1).withColor(EclipseClient.config.pingSelf ? 0x00c800 : 0xc80700));
                        }
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        x, 90, buttonWidth, buttonHeight,
                        Component.literal(call2).withColor(EclipseClient.config.pingOthers ? 0x00c800 : 0xc80700),
                        (btn) -> {
                            EclipseClient.config.pingOthers = !EclipseClient.config.pingOthers;
                            btn.setMessage(Component.literal(call2).withColor(EclipseClient.config.pingOthers ? 0x00c800 : 0xc80700));
                        }
                )
        );

        prefixTextArea = new EclipseTextArea(Minecraft.getInstance().font, x, 140, buttonWidth, 15, "Prefix");
        prefixTextArea.setValue(EclipseClient.config.pingPrefix.custom.toString());
        this.addRenderableWidget(prefixTextArea);
        this.addRenderableWidget(new EclipseRefreshButton(prefixTextArea, (btn) -> EclipseClient.config.pingPrefix.reset(prefixTextArea)));

        suffixTextArea = new EclipseTextArea(Minecraft.getInstance().font, x, 175, buttonWidth, 15, "Suffix");
        suffixTextArea.setValue(EclipseClient.config.pingSuffix.custom.toString());
        this.addRenderableWidget(suffixTextArea);
        this.addRenderableWidget(new EclipseRefreshButton(suffixTextArea, (btn) -> EclipseClient.config.pingSuffix.reset(suffixTextArea)));
    }

    @Override
    public void onClose() {
        minecraft.setScreen(
                new CustomScreen(Component.empty())
        );
        ConfigManager.save();
    }

    public void tick() {
        EclipseClient.config.pingPrefix.custom = prefixTextArea.getValue();
        EclipseClient.config.pingSuffix.custom = suffixTextArea.getValue();
    }
}

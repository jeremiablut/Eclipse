package com.eclipse.client.ConfigScreen;

import com.eclipse.client.EclipseClient;
import com.eclipse.client.config.ConfigManager;
import com.eclipse.client.enums.DayTime;
import com.eclipse.client.ui.EclipseButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static com.eclipse.client.ConfigScreen.CustomScreen.buttonHeight;
import static com.eclipse.client.ConfigScreen.CustomScreen.buttonWidth;

public class OverlayConfig extends Screen {
    public OverlayConfig(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int x = this.width / 2- buttonWidth / 2;

        this.addRenderableWidget(
                new EclipseButton(
                        x, 40, buttonWidth, buttonHeight,
                        Component.literal("NoFog").withColor(EclipseClient.config.nofog ? 0x00c800 : 0xc80700),
                        (btn) -> {
                            EclipseClient.config.nofog = !EclipseClient.config.nofog;
                            btn.setMessage(Component.literal("NoFog").withColor(EclipseClient.config.nofog ? 0x00c800 : 0xc80700));
                        }
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        x, 90, buttonWidth, buttonHeight,
                        Component.literal("NoPumpkin").withColor(EclipseClient.config.nopumpkin ? 0x00c800 : 0xc80700),
                        (btn) -> {
                            EclipseClient.config.nopumpkin = !EclipseClient.config.nopumpkin;
                            btn.setMessage(Component.literal("NoPumpkin").withColor(EclipseClient.config.nopumpkin ? 0x00c800 : 0xc80700));
                        }
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        x, 140, buttonWidth, buttonHeight,
                        Component.literal("Time").withColor(EclipseClient.config.dayTime == DayTime.DAY ? 0xffffff : EclipseClient.config.dayTime == DayTime.NIGHT ? 0x000000 : 0xff0901),
                        (btn) -> {
                            switch (EclipseClient.config.dayTime) {
                                case DAY -> EclipseClient.config.dayTime = DayTime.NIGHT;
                                case NIGHT -> EclipseClient.config.dayTime = DayTime.OFF;
                                case OFF -> EclipseClient.config.dayTime = DayTime.DAY;
                                default -> ConfigManager.save();
                            }
                            btn.setMessage(Component.literal("Time").withColor(EclipseClient.config.dayTime == DayTime.DAY ? 0xffffff : EclipseClient.config.dayTime == DayTime.NIGHT ? 0x000000 : 0xff0901));
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

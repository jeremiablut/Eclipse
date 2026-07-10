package com.eclipse.client.ConfigScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import com.eclipse.client.ui.EclipseButton;

public class CustomScreen extends Screen {
    public CustomScreen(Component title) {
        super(title);
    }

    private int w;
    public static int buttonWidth = 60, buttonHeight = 40;
    private static int distance = 10;

    public int ind(int order) {
        return order*buttonHeight + distance * order;
    }

    private int row(int index) {
        return w / 13 * index + buttonWidth;
    }

    @Override
    protected void init() {
        w = this.width;

        this.addRenderableWidget(
                new EclipseButton(
                        0, 40, 40, 20,
                        Component.literal("Move"),
                        (btn) -> Minecraft.getInstance().setScreen(new Drager(Component.empty()))
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        row(4), ind(1), buttonWidth, buttonHeight,
                        Component.literal("FPS"),
                        (btn) -> Minecraft.getInstance().setScreen(new FpsConfig(Component.empty()))
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        row(5), ind(1), buttonWidth, buttonHeight,
                        Component.literal("Timer"),
                        (btn) -> Minecraft.getInstance().setScreen(new TimerConfig(Component.empty()))
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        row(6), ind(1), buttonWidth, buttonHeight,
                        Component.literal("CPS"),
                        (btn) -> Minecraft.getInstance().setScreen(new CPSConfig(Component.empty()))
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        row(4), ind(2), buttonWidth, buttonHeight,
                        Component.literal("Ping"),
                        (btn) -> Minecraft.getInstance().setScreen(new PingConfig(Component.empty()))
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        row(5), ind(2), buttonWidth, buttonHeight,
                        Component.literal("Overlay"),
                        (btn) -> Minecraft.getInstance().setScreen(new OverlayConfig(Component.empty()))
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        row(6), ind(2), buttonWidth, buttonHeight,
                        Component.literal("Sprint"),
                        (btn) -> Minecraft.getInstance().setScreen(new SprintConfig(Component.empty()))
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        row(4), ind(3), buttonWidth, buttonHeight,
                        Component.literal("Gamma"),
                        (btn) -> Minecraft.getInstance().setScreen(new GammaConfig(Component.empty()))
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        row(5), ind(3), buttonWidth, buttonHeight,
                        Component.literal("Server"),
                        (btn) -> Minecraft.getInstance().setScreen(new ServerConfig(Component.empty()))
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        row(6), ind(3), buttonWidth, buttonHeight,
                        Component.literal("Armor"),
                        (btn) -> Minecraft.getInstance().setScreen(new ArmourConfig(Component.empty()))
                )
        );

        this.addRenderableWidget(
                new EclipseButton(
                        row(4), ind(4), buttonWidth, buttonHeight,
                        Component.literal("Witfit"),
                        (btn) -> Minecraft.getInstance().setScreen(new WitfitConfig(Component.empty()))
                )
        );
    }

    @Override
    public void tick() {
        buttonWidth = 60 * Minecraft.getInstance().getWindow().getWidth() / 2560;
        buttonHeight = 40 * Minecraft.getInstance().getWindow().getHeight() / 1440;
    }
}

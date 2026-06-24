package com.eclipse.client.ConfigScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class CustomScreen extends Screen {
    public CustomScreen(Component title) {
        super(title);
    }

    private int w;

    public int ind(int order) {
        return order*40;
    }

    private int row(int index) {
        return w / 5 * index;
    }

    @Override
    protected void init() {
        int buttonWidth = 120;
        int buttonHeight = 20;
        int x = this.width / 2 - buttonWidth / 2;
        w = this.width;

        this.addRenderableWidget(
                Button.builder(Component.literal("Move"), (btn) -> {
                    Minecraft.getInstance().setScreen(new Drager(Component.empty()));
                }).bounds(0, 40, 40, 20).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal("FPS"), (btn) -> {
                    Minecraft.getInstance().setScreen(new FpsConfig(Component.empty()));
                }).bounds(row(1), ind(1), buttonWidth, buttonHeight).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal("Timer"), (btn) -> {
                    Minecraft.getInstance().setScreen(new TimerConfig(Component.empty()));
                }).bounds(row(2), ind(1), buttonWidth, buttonHeight).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal("CPS"), (btn) -> {
                    Minecraft.getInstance().setScreen(new CPSConfig(Component.empty()));
                }).bounds(row(3), ind(1), buttonWidth, buttonHeight).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal("Ping"), (btn) -> {
                    Minecraft.getInstance().setScreen(new PingConfig(Component.empty()));
                }).bounds(row(1), ind(2), buttonWidth, buttonHeight).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal("NoFog"), (btn) -> {
                    Minecraft.getInstance().setScreen(new FogConfig(Component.empty()));
                }).bounds(row(2), ind(2), buttonWidth, buttonHeight).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal("Sprint"), (btn) -> {
                    Minecraft.getInstance().setScreen(new SprintConfig(Component.empty()));
                }).bounds(row(3), ind(2), buttonWidth, buttonHeight).build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal("Gamma"), (btn) -> {
                    Minecraft.getInstance().setScreen(new GammaConfig(Component.empty()));
                }).bounds(row(1), ind(3), buttonWidth, buttonHeight).build()
        );
    }
}

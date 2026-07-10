package com.eclipse.client.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class EclipseRefreshButton extends Button {
    public EclipseRefreshButton(EclipseTextArea e, OnPress onPress) {
        super(e.getX() + e.getWidth(), e.getY(), e.getHeight(), e.getHeight(), Component.literal("⟳"), onPress, DEFAULT_NARRATION);
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {

        boolean hovered = this.isHovered();

        int bgColor = hovered ? 0xAA000000 : 0x88000000;

        graphics.fill(
                this.getX(),
                this.getY(),
                this.getX() + this.width,
                this.getY() + this.height,
                bgColor
        );

        graphics.centeredText(
                Minecraft.getInstance().font,
                this.getMessage(),
                this.getX() + this.width / 2,
                this.getY() + (this.height - 8) / 2,
                0xFFFFFFFF
        );
    }
}

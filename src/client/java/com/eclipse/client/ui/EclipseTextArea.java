package com.eclipse.client.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public class EclipseTextArea extends EditBox {
    public EclipseTextArea(Font font, int x, int y, int width, int height, String info) {
        super(font, x, y, width, height, Component.literal(info));
    }

    @Override
    public void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {

        graphics.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), 0x88000000);
        graphics.outline(getX(), getY(), getWidth(), getHeight(), 0xFFFFFFFF);

        String text = getValue();

        graphics.text(Minecraft.getInstance().font, getMessage(), getX() - Minecraft.getInstance().font.width(getMessage()) - 4, getY() + 4, 0xc6c8c6c8, false);
        graphics.text(Minecraft.getInstance().font, text, getX() + 4, getY() + 4, 0xfdc8c6c8, false);
    }
}
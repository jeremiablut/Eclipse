package com.eclipse.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import static com.eclipse.client.EclipseClient.selected;

public class WidgetHUD {
    private int width, height;
    private static final Minecraft minecraft = Minecraft.getInstance();

    public boolean isHovering;
    public String text;
    public Vec2I pos, oldPos;
    public int selct, color = 0xFFFFFFFF;

    public WidgetHUD(Vec2I pos, String text, Vec2I oldPos) {
        this.text = text;
        this.pos = pos;
        this.oldPos = oldPos;
        this.selct = oldPos.y / 10;
    }

    public void render(GuiGraphicsExtractor graphics) {
        if (text == null) return;
        graphics.text(
                minecraft.font,
                text,
                pos.x,
                pos.y,
                color
        );
    }

    public Vec2I reset() {
        this.pos = new Vec2I(oldPos.x, oldPos.y);
        return this.pos;
    }

    public void refresh() {
        double mouseX = minecraft.mouseHandler.xpos()
                * minecraft.getWindow().getGuiScaledWidth()
                / minecraft.getWindow().getScreenWidth();

        double mouseY = minecraft.mouseHandler.ypos()
                * minecraft.getWindow().getGuiScaledHeight()
                / minecraft.getWindow().getScreenHeight();

        width = minecraft.font.width(text);
        height = minecraft.font.lineHeight;

        isHovering =
                mouseX >= pos.x &&
                        mouseX <= pos.x + width &&
                        mouseY >= pos.y &&
                        mouseY <= pos.y + height;

        if (isHovering && (selected == 0 || selected == selct)) {
            pos.x = ((int) (mouseX - width / 2) / 10) * 10;
            pos.y = ((int) (mouseY + height / 2) / 10) * 10;
            selected = selct;
        } else if (selected == selct) {
            pos.x = ((int) (mouseX - width / 2) / 10) * 10;
            pos.y = ((int) (mouseY + height / 2) / 10) * 10;
        }
    }
}
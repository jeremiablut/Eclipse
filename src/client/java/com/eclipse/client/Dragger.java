package com.eclipse.client;

import net.minecraft.client.Minecraft;

import static com.eclipse.client.EclipseClient.selected;

public class Dragger {

    private int x, y, selct;

    public Dragger(int X, int Y, int selct) {
        this.x = X;
        this.y = Y;
        this.selct = selct;
    }

    private int width, height;

    private static Minecraft minecraft = Minecraft.getInstance();

    private boolean isHover;

    public int getX() {
        return x;
    };

    public int getY() {
        return y;
    };

    public boolean isHovering() { return this.isHover; }

    public void refresh(String value) {
        double mouseX = minecraft.mouseHandler.xpos()
                * minecraft.getWindow().getGuiScaledWidth()
                / minecraft.getWindow().getScreenWidth();

        double mouseY = minecraft.mouseHandler.ypos()
                * minecraft.getWindow().getGuiScaledHeight()
                / minecraft.getWindow().getScreenHeight();

        width = minecraft.font.width(value);
        height = minecraft.font.lineHeight;

        isHover =
                mouseX >= x &&
                        mouseX <= x + width &&
                        mouseY >= y &&
                        mouseY <= y + height;

        if (isHover && (selected == 0 || selected == selct)) {
            x = ((int) (mouseX - width / 2) / 10) * 10;
            y = ((int) (mouseY + height / 2) / 10) * 10;
            selected = selct;
        } else if (selected == selct) {
            x = ((int) (mouseX - width / 2) / 10) * 10;
            y = ((int) (mouseY + height / 2) / 10) * 10;
        }
    }
}

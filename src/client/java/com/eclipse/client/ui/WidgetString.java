package com.eclipse.client.ui;

import com.eclipse.client.Vec2I;
import net.minecraft.client.Minecraft;

import static com.eclipse.client.EclipseClient.selected;

public class WidgetString extends Widget {
    private int widthLocal, heightLocal;
    private static final Minecraft minecraft = Minecraft.getInstance();

    public boolean isHovering;
    public String text;
    public Vec2I posLocal, oldPosLocal;
    public int selct;
    public int color = 0xFFFFFFFF;

    public WidgetString(Vec2I pos, String text, Vec2I oldPos) {
        super(pos, oldPos, (graphics) -> {},
                10,
                10
                );
        this.text = text;
        this.posLocal = pos;
        this.oldPosLocal = oldPos;
        this.selct = oldPosLocal.y / 10;
        this.widthLocal = 10;
        this.heightLocal = 10;
        this.graphicer = (graphics) -> {
            graphics.text(
                minecraft.font,
                this.text,
                posLocal.x,
                posLocal.y,
                this.color
        );};
    }

    public Vec2I reset() {
        this.posLocal = this.oldPosLocal;
        return this.posLocal;
    }

    @Override
    public void refresh() {
        double mouseX = minecraft.mouseHandler.xpos()
                * minecraft.getWindow().getGuiScaledWidth()
                / minecraft.getWindow().getScreenWidth();

        double mouseY = minecraft.mouseHandler.ypos()
                * minecraft.getWindow().getGuiScaledHeight()
                / minecraft.getWindow().getScreenHeight();

        this.widthLocal = minecraft.font.width(this.text);
        this.heightLocal = minecraft.font.lineHeight;

        this.isHovering =
                mouseX >= posLocal.x &&
                        mouseX <= posLocal.x + widthLocal &&
                        mouseY >= posLocal.y &&
                        mouseY <= posLocal.y + heightLocal;

        if (isHovering && (selected == 0 || selected == selct)) {
            posLocal.x = ((int) (mouseX - widthLocal / 2) / 10) * 10;
            posLocal.y = ((int) (mouseY + heightLocal / 2) / 10) * 10;
            selected = selct;
        } else if (selected == selct) {
            posLocal.x = ((int) (mouseX - widthLocal / 2) / 10) * 10;
            posLocal.y = ((int) (mouseY + heightLocal / 2) / 10) * 10;
        }
    }
}
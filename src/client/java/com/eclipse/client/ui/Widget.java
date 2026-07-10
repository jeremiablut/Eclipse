package com.eclipse.client.ui;

import com.eclipse.client.Vec2I;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import java.util.function.Consumer;

import static com.eclipse.client.EclipseClient.selected;

public class Widget {
    private int width, height;
    private static final Minecraft minecraft = Minecraft.getInstance();

    public Consumer<GuiGraphicsExtractor> graphicer;
    public boolean isHovering;
    public Vec2I pos, oldPos;
    public int selct;

    public Widget(Vec2I pos, Vec2I oldPos, Consumer<GuiGraphicsExtractor> graphicer, int width, int height) {
        this.pos = pos;
        this.oldPos = oldPos;
        this.selct = oldPos.y / 10;
        this.graphicer = graphicer;
        this.width = width;
        this.height = height;
    }

    public void render(GuiGraphicsExtractor graphics) {
        graphicer.accept(graphics);
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

        isHovering =
                mouseX >= pos.x &&
                        mouseX <= pos.x + width &&
                        mouseY >= pos.y &&
                        mouseY <= pos.y + height;

        if (isHovering && (selected == 0 || selected == selct)) {
            pos.x = ((int)((mouseX - width / 2) / 10) * 10);
            pos.y = ((int)((mouseY - height / 2) / 10) * 10);
            selected = selct;
        } else if (selected == selct) {
            pos.x = ((int)((mouseX - width / 2) / 10) * 10);
            pos.y = ((int)((mouseY - height / 2) / 10) * 10);
        }
    }
}

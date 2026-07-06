package com.eclipse.client.mixin;

import com.eclipse.client.ConfigScreen.CustomScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PauseScreen.class)
public abstract class PauseScreenMixin extends Screen {

    protected PauseScreenMixin(Component title) {
        super(title);
    }

    private int rowCalculater(int index) {
        for (var widget : this.children()) {
            if (widget instanceof Button button) {
                if (button.getMessage().getString().equals("Save and Quit to Title")
                        || button.getMessage().getString().equals("menu.quit")) {

                    return button.getY() + button.getHeight() + 4; // + spacing
                }
            }
        }

        return width / 2;
    }

    @Inject(method = "init", at = @At("TAIL"), cancellable = true)
    private void init(CallbackInfo ci) {
        int buttonWidth = 204;
        int buttonHeight = 20;

        this.addRenderableWidget(
                Button.builder(Component.literal("Eclipse settings"), (btn) -> {
                    Minecraft.getInstance().setScreen(new CustomScreen(Component.empty()));
                }).bounds(this.width / 2 - buttonWidth / 2, rowCalculater(6), buttonWidth, buttonHeight).build()
        );
    }
}

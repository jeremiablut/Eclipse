package com.eclipse.client.mixin;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.TitleScreen;

import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    private static final Identifier ECLIPSE_LOGO =
            Identifier.fromNamespaceAndPath("eclipse", "textures/gui/text.png");

    @Inject(
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/LogoRenderer;extractRenderState(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IF)V",
                    shift = At.Shift.AFTER
            )
    )
    private void eclipse$render(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta, CallbackInfo ci) {

        int texWidth = 1024;
        int texHeight = 319;

        float aspect = texHeight / (float) texWidth;

        int renderWidth = 100;
        int renderHeight = (int)(renderWidth * aspect);

        int x = graphics.guiWidth() / 2 - renderWidth / 2;
        int y = 75;

        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                ECLIPSE_LOGO,
                x, y,
                0, 0,
                renderWidth, renderHeight,
                texWidth, texHeight,
                texWidth, texHeight
        );
    }

}

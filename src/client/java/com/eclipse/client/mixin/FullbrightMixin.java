package com.eclipse.client.mixin;

import net.minecraft.client.renderer.Lightmap;
import net.minecraft.client.renderer.state.LightmapRenderState;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.eclipse.client.EclipseClient.getGamma;

@Mixin(Lightmap.class)
public class FullbrightMixin {

    @Inject(method = "render", at = @At("HEAD"))
    private void fullbright(LightmapRenderState state, CallbackInfo ci) {

        if (!getGamma()) return;

        state.brightness = 1.0F;
        state.blockFactor = 1.0F;
        state.skyFactor = 1.0F;

        state.darknessEffectScale = 0.0F;
        state.bossOverlayWorldDarkening = 0.0F;
        state.nightVisionEffectIntensity = 1.0F;

        state.ambientColor = new Vector3f(1F, 1F, 1F);
        state.skyLightColor = new Vector3f(1F, 1F, 1F);
        state.blockLightTint = new Vector3f(1F, 1F, 1F);
        state.nightVisionColor = new Vector3f(1F, 1F, 1F);

        state.needsUpdate = true;
    }
}

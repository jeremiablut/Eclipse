package com.eclipse.client.mixin;

import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.FogRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.eclipse.client.config.ConfigManager.config;

@Mixin(FogRenderer.class)
public class FogRemoverMixin {

    @Inject(method = "setupFog", at = @At("RETURN"), cancellable = true)
    private void setupFog(CallbackInfoReturnable<FogData> cir) {
        if (!config.nofog) return;
        FogData fog = cir.getReturnValue();

        fog.renderDistanceStart = 999999f;
        fog.renderDistanceEnd = 1000000f;

        fog.environmentalStart = 999999f;
        fog.environmentalEnd = 1000000f;

        cir.setReturnValue(fog);
    }
}

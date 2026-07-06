package com.eclipse.client.mixin;

import com.eclipse.client.EclipseClient;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import net.minecraft.client.renderer.fog.FogRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FogRenderer.class)
public class FogRemoverMixin {

    @Shadow @Final
    private GpuBuffer emptyBuffer;

    @Shadow
    public static int FOG_UBO_SIZE;

    @Inject(method = "getBuffer", at = @At("HEAD"), cancellable = true)
    private void eclipse$removeFog(FogRenderer.FogMode mode, CallbackInfoReturnable<GpuBufferSlice> cir) {

        if (EclipseClient.getNoFog()) {
            cir.setReturnValue(emptyBuffer.slice(0L, (long) FOG_UBO_SIZE));
        }
    }
}
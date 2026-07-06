package com.eclipse.client.mixin;

import com.eclipse.client.EclipseClient;
import net.minecraft.client.renderer.SectionOcclusionGraph;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SectionOcclusionGraph.class)
public class ChunkCullingMixin {

    @ModifyVariable(
            method = "runUpdates",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true
    )
    private boolean disableSmartCull(boolean smartCull) {
        if (!EclipseClient.freecam) {return true;}
        return false;
    }
}

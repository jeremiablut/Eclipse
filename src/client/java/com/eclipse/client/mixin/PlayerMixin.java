package com.eclipse.client.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.eclipse.client.EclipseClient.freecam;

@Mixin(LevelRenderer.class)
public class PlayerMixin {
    @Shadow
    protected EntityRenderState extractEntity(Entity e, float g) {
        return null;
    }

    @Inject(method = "extractVisibleEntities", at = @At(value = "RETURN"))
    private void onExtractVisibleEntities(Camera camera, Frustum frustum, DeltaTracker deltaTracker, LevelRenderState renderState, CallbackInfo ci) {
        if (Minecraft.getInstance().level != null && freecam) {
            Entity player = Minecraft.getInstance().player;
            TickRateManager tickRateManager = Minecraft.getInstance().level.tickRateManager();
            float g = deltaTracker.getGameTimeDeltaPartialTick(!tickRateManager.isEntityFrozen(player));
            EntityRenderState entityRenderState = this.extractEntity(player, g);
            renderState.entityRenderStates.add(entityRenderState);
        }
    }
}

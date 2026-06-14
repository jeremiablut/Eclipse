package com.eclipse.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.eclipse.client.EclipseClient.getSprint;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "spawnSprintParticle", at = @At("HEAD"), cancellable = true)
    private void spawnSprintParticle(CallbackInfo ci) {
        if (getSprint() && (Object) this == Minecraft.getInstance().player) {
            ci.cancel();
        }
    }
}


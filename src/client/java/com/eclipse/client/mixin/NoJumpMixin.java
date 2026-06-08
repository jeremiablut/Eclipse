package com.eclipse.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.eclipse.client.EclipseClient.freecam;

@Mixin(LivingEntity.class)
public class NoJumpMixin {

    @Inject(method="isJumping", at = @At("HEAD"), cancellable = true)
    private void isJumping(CallbackInfoReturnable<Boolean> cir) {
        if (!this.equals(Minecraft.getInstance().player) || !freecam) return;
        cir.setReturnValue(false);
    }
}

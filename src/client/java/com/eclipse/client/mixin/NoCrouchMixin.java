package com.eclipse.client.mixin;

import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.eclipse.client.EclipseClient.freecam;

@Mixin(LocalPlayer.class)
public class NoCrouchMixin {
    @Inject(method="isCrouching", at = @At("HEAD"), cancellable = true)
    private void isCrouching(CallbackInfoReturnable<Boolean> cir) {
        if (freecam) cir.setReturnValue(false);
    }

    @Inject(method = "isShiftKeyDown", at = @At("HEAD"), cancellable = true)
    private void isShiftKeyDown(CallbackInfoReturnable<Boolean> cir) {
        if (freecam) cir.setReturnValue(false);
    }
}

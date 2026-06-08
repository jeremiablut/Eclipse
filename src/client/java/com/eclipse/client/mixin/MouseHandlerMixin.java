package com.eclipse.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.eclipse.client.EclipseClient.freecam;
import static com.eclipse.client.EclipseClient.freecamEntity;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {

    @Inject(method = "turnPlayer", at = @At("TAIL"))
    private void onTurnPlayer(CallbackInfo ci) {
        if (!freecam) return;
        if (freecamEntity == null) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;


        freecamEntity.setYRot(mc.player.getYRot());
        freecamEntity.setXRot(mc.player.getXRot());
    }
}


package com.eclipse.client.mixin;

import com.eclipse.client.EclipseClient;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.protocol.game.ClientboundPlayerRotationPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientboundPlayerRotationPacket.class)
public class UnboundRotationMixin {
    @Inject(method = "type", at = @At("HEAD"), cancellable = true)
    private void type(CallbackInfoReturnable<PacketType<ClientboundPlayerRotationPacket>> cir) {
        if (EclipseClient.freecam) {
            cir.setReturnValue(null);
        }
    }
}

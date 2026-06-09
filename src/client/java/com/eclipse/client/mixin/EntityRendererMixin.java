package com.eclipse.client.mixin;

import com.eclipse.client.PackagerClient;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin<T extends Entity> {

    @Inject(method = "getNameTag", at = @At("RETURN"), cancellable = true)
    private void eclipse$injectPrefix(T entity, CallbackInfoReturnable<Component> cir) {
        if (entity instanceof Player player) {
            UUID playerUuid = player.getUUID();

            if (PackagerClient.onlineEclipsePlayers.contains(playerUuid)) {
                Component originalName = cir.getReturnValue();

                if (originalName != null) {
                    Component prefixedName = Component.literal("§f[§4EP§f] ")
                            .append(originalName);

                    cir.setReturnValue(prefixedName);
                }
            }
        }
    }
}

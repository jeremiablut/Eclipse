package com.eclipse.client.mixin;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

import static com.eclipse.client.EclipseClient.eclipsePlayerPrefixes;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin<T extends Entity> {

    @Inject(method = "getNameTag", at = @At("RETURN"), cancellable = true)
    private void eclipse$injectPrefix(T entity, CallbackInfoReturnable<Component> cir) {
        if (!(entity instanceof Player player)) return;

        UUID uuid = player.getUUID();

        String prefixString = eclipsePlayerPrefixes.get(uuid);
        if (prefixString == null || prefixString.isEmpty()) return;

        Component originalName = cir.getReturnValue();
        if (originalName == null) return;

        if (originalName.getString().startsWith(prefixString)) return;

        MutableComponent newName = Component.literal(prefixString)
                .append(originalName);

        cir.setReturnValue(newName);
    }
}

package com.eclipse.client.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.client.multiplayer.PlayerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

import static com.eclipse.client.EclipseClient.eclipsePlayerPrefixes;

@Mixin(PlayerTabOverlay.class)
public class TabListMixin {

    @Inject(method = "getNameForDisplay", at = @At("RETURN"), cancellable = true)
    private void addEclipsePrefix(PlayerInfo playerInfo, CallbackInfoReturnable<Component> cir) {
        GameProfile profile = playerInfo.getProfile();
        UUID uuid = profile.id();

        String prefixString = eclipsePlayerPrefixes.get(uuid);

        if (prefixString == null || prefixString.isEmpty()) return;

        Component original = cir.getReturnValue();
        if (original == null) return;

        if (original.getString().startsWith(prefixString)) return;

        MutableComponent newName = Component.literal(prefixString)
                .append(original);

        cir.setReturnValue(newName);
    }
}




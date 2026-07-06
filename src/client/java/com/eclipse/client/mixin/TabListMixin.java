package com.eclipse.client.mixin;

import com.eclipse.client.EclipseClient;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.client.multiplayer.PlayerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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

    @Inject(method = "extractPingIcon", at = @At("HEAD"), cancellable = true)
    private void replacePing(GuiGraphicsExtractor graphics, int slotWidth, int xo, int yo, PlayerInfo info, CallbackInfo ci) {
        if (EclipseClient.getPingOthers()) {
            Minecraft mc = Minecraft.getInstance();

            if (info == null) {
                ci.cancel();
                return;
            }


            String text = info.getLatency() + "ms";

            int x = xo + slotWidth - mc.font.width(text) - 4;

            graphics.text(mc.font, Component.literal(text).withColor(info.getLatency() < 50 ? 0x00FF00 : info.getLatency() < 200 ? 0xFFA500 : 0xFF0000), x, yo, -1);

            ci.cancel();
        }
    }
}
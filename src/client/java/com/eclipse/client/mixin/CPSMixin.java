package com.eclipse.client.mixin;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.eclipse.client.EclipseClient.leftCPS;
import static com.eclipse.client.EclipseClient.rightCPS;

@Mixin(KeyMapping.class)
public class CPSMixin {

    @Inject(method = "setDown", at = @At("HEAD"))
    private void setDown(boolean pressed, CallbackInfo ci) {
        if (!pressed) return;
        Minecraft mc = Minecraft.getInstance();

        if (mc.options.keyAttack == (Object)this) {
            leftCPS.register();
        }

        if (mc.options.keyUse == (Object)this) {
            rightCPS.register();
        }
    }
}

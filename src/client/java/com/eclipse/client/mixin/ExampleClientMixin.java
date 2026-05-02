package com.eclipse.client.mixin;

import com.eclipse.client.EclipseClient;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public class ExampleClientMixin {
	@Inject(method = "renderArmWithItem", at = @At("HEAD"), cancellable = true)
	private void onRender(
            AbstractClientPlayer player,
            float partialTicks,
            float pitch,
            InteractionHand hand,
            float swingProgress,
            ItemStack stack,
            float equipProgress,
            PoseStack poseStack,
            SubmitNodeCollector buffer,
            int light,
            CallbackInfo ci
	) {
		if (hand == InteractionHand.MAIN_HAND && EclipseClient.freecam) {
			ci.cancel();
		}
	}
}
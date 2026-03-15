package com.eclipsew;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class EclipseClient implements ClientModInitializer {
	private boolean fps = false, sprint = true;
	private void setPvp() {
		Minecraft.getInstance().options.bobView().set(false);
		Minecraft.getInstance().options.vignette().set(false);
		Minecraft.getInstance().options.damageTiltStrength().set(0d);
		Minecraft.getInstance().options.narratorHotkey().set(false);
		Minecraft.getInstance().options.enableVsync().set(false);
		Minecraft.getInstance().options.fovEffectScale().set(0d);
		Minecraft.getInstance().options.entityShadows().set(false);
		Minecraft.getInstance().options.save();
	}
	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register((minecraft -> {
			var activePlayer = minecraft.player;

			if (activePlayer == null) return;

			if (fps) {
				activePlayer.displayClientMessage(Component.literal(String.valueOf(minecraft.getFps())).withColor(0x00FFFF), true);
			}
			if (sprint) {
				activePlayer.setSprinting(true);
			}
		}));
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(ClientCommandManager.literal("toggle.fps").executes(context -> {
				fps = !fps;
				context.getSource().sendFeedback(Component.literal("FPS toggled to " + fps));
				return 1;
			}));

			dispatcher.register(ClientCommandManager.literal("toggle.sprint").executes(context -> {
				sprint = !sprint;
				context.getSource().sendFeedback(Component.literal("Sprint toggled to " + sprint));
				return 1;
			}));

			dispatcher.register(ClientCommandManager.literal("renderreset").executes(context -> {
				Minecraft.getInstance().gameRenderer.resetData();
				context.getSource().sendFeedback(Component.literal("renderengine reseted"));
				return 1;
			}));

			dispatcher.register(ClientCommandManager.literal("enable.pvp").executes(context -> {
				setPvp();
				context.getSource().sendFeedback(Component.literal("PVP options set"));
				return 1;
			}));
		});
	}
}
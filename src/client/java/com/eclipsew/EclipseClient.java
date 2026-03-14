package com.eclipsew;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.awt.*;

public class EclipseClient implements ClientModInitializer {
	private Minecraft minecraft;
	private Player player;
	private boolean fps = false, sprint = true, sneak = false;
	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register((minecraft_ -> {
			player = minecraft_.player;
			minecraft = minecraft_;
			Integer ifps = minecraft.getFps();
			Color color = Color.CYAN;
			if (player != null) {
				if (fps) {
					Component component = Component.literal(String.valueOf(ifps))
						.withColor(color.getRGB()).withoutShadow();
					player.displayClientMessage(component, true);
				}
				if (sprint) {
					player.setSprinting(true);
				}
				if (sneak) {
					player.setShiftKeyDown(true);
				}
			}
		}));

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(ClientCommandManager.literal("toggle.fps").executes(context -> {
                fps = !fps;
				Component component = Component.literal("FPS toggled to " + fps);
				player.displayClientMessage(component, false);
				return 1;
			}));
		});

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(ClientCommandManager.literal("toggle.sprint").executes(context -> {
				sprint = !sprint;
				Component component = Component.literal("SPRINT toggled to " + sprint);
				player.displayClientMessage(component, false);
				return 1;
			}));
		});

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(ClientCommandManager.literal("toggle.sneak").executes(context -> {
				sneak = !sneak;
				Component component = Component.literal("SNEAK toggled to " + sneak);
				player.displayClientMessage(component, false);
				return 1;
			}));
		});
	}
}

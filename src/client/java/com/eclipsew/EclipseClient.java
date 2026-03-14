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
	private boolean fps = false, sprint = true;
	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register((minecraft_ -> {
			player = minecraft_.player;
			minecraft = minecraft_;
			if (player != null) {
				if (fps) {
					player.displayClientMessage(Component.literal(String.valueOf(minecraft_.getFps()))
							.withColor(Color.CYAN.getRGB()), true);
				}
				if (sprint) {
					player.setSprinting(true);
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
			dispatcher.register(ClientCommandManager.literal("crash").executes(context -> {
				minecraft.stop();
				return 1;
			}));
		});

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(ClientCommandManager.literal("renderreset").executes(context -> {
				minecraft.gameRenderer.resetData();
				return 1;
			}));
		});
	}
}

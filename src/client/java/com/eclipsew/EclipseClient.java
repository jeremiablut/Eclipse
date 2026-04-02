package com.eclipsew;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.player.Player;

public class EclipseClient implements ClientModInitializer {
	private boolean fps = false, sprint = true, ran = false, freecam = false;
	private Interaction interaction;
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
			if (freecam) {
				interaction.setXRot(minecraft.player.getXRot());
				interaction.setYRot(minecraft.player.getYRot());
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

			dispatcher.register(ClientCommandManager.literal("enable.pvp").executes(context -> {
				setPvp();
				context.getSource().sendFeedback(Component.literal("PVP options set"));
				return 1;
			}));

			dispatcher.register(ClientCommandManager.literal("reload").executes(context -> {
				context.getSource().getClient().reloadResourcePacks();
				context.getSource().sendFeedback(Component.literal("Reloaded!"));
				return 1;
			}));

			dispatcher.register(ClientCommandManager.literal("freecam").executes(context -> {
				interaction = new Interaction(EntityType.INTERACTION, context.getSource().getPlayer().level());
				interaction.setPos(context.getSource().getPlayer().getX(), context.getSource().getPlayer().getY() + 1.5, context.getSource().getPlayer().getZ());
				interaction.setXRot(context.getSource().getPlayer().getXRot());
				interaction.setYRot(context.getSource().getPlayer().getYRot());
				context.getSource().getWorld().addEntity(interaction);
				context.getSource().getClient().setCameraEntity(interaction);
				freecam = !freecam;
				if (!freecam) {
					context.getSource().getClient().setCameraEntity(context.getSource().getPlayer());
					interaction.discard();
				}
				return 1;
			}));
		});
	}
}
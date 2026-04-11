package com.eclipse.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.glfw.GLFW;

public class EclipseClient implements ClientModInitializer {
	private boolean fps = false, sprint = true, freecam = false, cacheSprint, shown = false, digital = true;
	private Interaction freecamEntity;
	private float distance = 1f;
	private String status, hoursToMinutesDigital, minutesToSecondsDigital, hoursToMinutesAnalog, minutesToSecondsAnalog, timer;
	private int ticks = 0, seconds, minutes = 0, hours = 0;

	private void setPvp() {
		Minecraft.getInstance().options.bobView().set(false);
		Minecraft.getInstance().options.vignette().set(false);
		Minecraft.getInstance().options.damageTiltStrength().set(0d);
		Minecraft.getInstance().options.narratorHotkey().set(false);
		Minecraft.getInstance().options.fovEffectScale().set(0d);
		Minecraft.getInstance().options.entityShadows().set(false);
		Minecraft.getInstance().options.save();
	}

	public void genTimer() {
		if (minutes < 10) {
			hoursToMinutesDigital = ":0";
			hoursToMinutesAnalog = "h 0";
		}

		else {
			hoursToMinutesDigital = ":";
			hoursToMinutesAnalog = "h ";
		}

		if (seconds < 10) {
			minutesToSecondsDigital = ":0";
			minutesToSecondsAnalog = "min 0";
		}

		else {
			minutesToSecondsDigital = ":";
			minutesToSecondsAnalog = "min ";
		}

		if (digital) {
			if (hours == 0) {
				if (minutes == 0) {
					timer = seconds + "";
				}
				else {
					timer = minutes + minutesToSecondsDigital + seconds;
				}
			}
			else {
				timer = hours + hoursToMinutesDigital + minutes + minutesToSecondsDigital + seconds;
			}
		}
		else {
			if (hours == 0) {
				if (minutes == 0) {
					timer = seconds + "s";
				}
				else {
					timer = minutes + minutesToSecondsAnalog + seconds + "s";
				}
			}
			else {
				timer = hours + hoursToMinutesAnalog + minutes + minutesToSecondsAnalog + seconds + "s";
			}

		}

	}

	@Override
	public void onInitializeClient() {

		ClientTickEvents.END_CLIENT_TICK.register((minecraft -> {

			var activePlayer = minecraft.player;

			Window window = Minecraft.getInstance().getWindow();

			if (activePlayer == null) return;

			if (fps) activePlayer.sendOverlayMessage(Component.literal(String.valueOf(minecraft.getFps())).withColor(0x00FFFF));

			if (sprint) activePlayer.setSprinting(true);

			if (freecam) {
				freecamEntity.setXRot(minecraft.player.getXRot());
				freecamEntity.setYRot(minecraft.player.getYRot());

				if (activePlayer.isShiftKeyDown()) distance = 2;
				else distance = 1;

				if (InputConstants.isKeyDown(window, GLFW.GLFW_KEY_SPACE)) {
					freecamEntity.setPos(freecamEntity.getX(), freecamEntity.getY() + 1, freecamEntity.getZ());
				}

				if (InputConstants.isKeyDown(window, GLFW.GLFW_KEY_W)) {
					Vec3 look = freecamEntity.getLookAngle();
					freecamEntity.setPos(freecamEntity.getX() + (look.x * distance), freecamEntity.getY() + (look.y * distance), freecamEntity.getZ() + (look.z * distance));
				}

				if (InputConstants.isKeyDown(window, GLFW.GLFW_KEY_S)) {
					Vec3 look = freecamEntity.getLookAngle();
					freecamEntity.setPos(freecamEntity.getX() + (look.x * -distance), freecamEntity.getY() + (look.y * -distance), freecamEntity.getZ() + (look.z * -distance));
				}

				if (InputConstants.isKeyDown(window, GLFW.GLFW_KEY_A)) {
					Vec3 look = freecamEntity.getLookAngle();
					Vec3 right = new Vec3(-look.z, 0, look.x).normalize();
					freecamEntity.setPos(freecamEntity.getX() - (right.x * distance), freecamEntity.getY(), freecamEntity.getZ() - (right.z * distance));
				}


				if (InputConstants.isKeyDown(window, GLFW.GLFW_KEY_D)) {
					Vec3 look = freecamEntity.getLookAngle();
					Vec3 right = new Vec3(-look.z, 0, look.x).normalize();
					freecamEntity.setPos(freecamEntity.getX() + (right.x * distance), freecamEntity.getY(), freecamEntity.getZ() + (right.z * distance));
				}
			}

			{
				seconds = ticks / 20;

				if ("started".equals(status)) ticks++;
				genTimer();
				Component component = Component.nullToEmpty(timer)
						.copy()
						.withStyle(style -> style.withColor(0x0000AA)
								.withShadowColor(0x000000));
				if (shown) minecraft.player.sendOverlayMessage(component);
				if (seconds >= 60) {
					ticks = 0;
					if (minutes < 60) minutes++;
					else {
						minutes = 0;
						hours++;
					}
				}
			}
		}));

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			// FPS Toggle Command
			dispatcher.register(ClientCommands.literal("toggle.fps")
					.executes(context -> {
						fps = !fps;
						context.getSource().sendFeedback(Component.literal("FPS toggled to " + fps));
						return 1;
					})
			);

			// Sprint Toggle Command
			dispatcher.register(ClientCommands.literal("toggle.sprint")
					.executes(context -> {
						sprint = !sprint;
						context.getSource().sendFeedback(Component.literal("Sprint toggled to " + sprint));
						return 1;
					})
			);

			// PVP Command
			dispatcher.register(ClientCommands.literal("enable.pvp")
					.executes(context -> {
						setPvp();
						context.getSource().sendFeedback(Component.literal("PVP options set"));
						return 1;
					})
			);

			// Resource Pack Reload
			dispatcher.register(ClientCommands.literal("reload")
					.executes(context -> {
						context.getSource().getClient().reloadResourcePacks();
						context.getSource().sendFeedback(Component.literal("Reloaded!"));
						return 1;
					})
			);

			// Freecam Command
			dispatcher.register(ClientCommands.literal("freecam")
					.executes(context -> {
						LocalPlayer player = context.getSource().getPlayer();
						if (!freecam) {
							freecamEntity = new Interaction(EntityType.INTERACTION, player.level());
							freecamEntity.setPos(player.getX(), player.getY() + 2, player.getZ());
							context.getSource().getLevel().addEntity(freecamEntity);
							context.getSource().getClient().setCameraEntity(freecamEntity);
							cacheSprint = sprint;
							sprint = false;
						}
						freecam = !freecam;
						if (!freecam) {
							sprint = cacheSprint;
							context.getSource().getClient().setCameraEntity(player);
							freecamEntity.discard();
							freecamEntity = null;
						}
						context.getSource().sendFeedback(Component.literal("Freecam is now " + freecam));
						return 1;
					})
			);

			// Timer Commands
			dispatcher.register(ClientCommands.literal("timer.start")
					.executes(context -> {
						status = "started";
						context.getSource().getPlayer().sendOverlayMessage(Component.literal("Timer Started"));
						return 1;
					})
			);

			dispatcher.register(ClientCommands.literal("timer.restart")
					.executes(context -> {
						status = "restarted";
						ticks = 0;
						seconds = 0;
						minutes = 0;
						hours = 0;
						context.getSource().getPlayer().sendOverlayMessage(Component.literal("Timer was Restarted"));
						return 1;
					})
			);

			dispatcher.register(ClientCommands.literal("timer.stop")
					.executes(context -> {
						status = "stopped";
						context.getSource().getPlayer().sendOverlayMessage(Component.literal("Timer was stopped"));
						return 1;
					})
			);

			dispatcher.register(ClientCommands.literal("timer.toggle")
					.executes(context -> {
						shown = !shown;
						context.getSource().getPlayer().sendOverlayMessage(Component.literal("Timer was toggled"));
						return 1;
					})
			);

			dispatcher.register(ClientCommands.literal("timer.toggle.mode")
					.executes(context -> {
						digital = !digital;
						return 1;
					})
			);
		});

	}
}
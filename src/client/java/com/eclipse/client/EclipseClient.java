package com.eclipse.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.glfw.GLFW;

public class EclipseClient implements ClientModInitializer {
	KeyMapping.Category CATEGORY = KeyMapping.Category.register(
			Identifier.fromNamespaceAndPath("eclipse", "controlys")
	);
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

	private void toggleFreecam() {
		if (!freecam) {
			freecamEntity = new Interaction(EntityType.INTERACTION, Minecraft.getInstance().player.level());
			freecamEntity.setPos(Minecraft.getInstance().player.getX(), Minecraft.getInstance().player.getY() + 2, Minecraft.getInstance().player.getZ());
			Minecraft.getInstance().level.addEntity(freecamEntity);
			Minecraft.getInstance().setCameraEntity(freecamEntity);
			cacheSprint = sprint;
			sprint = false;
		}
		freecam = !freecam;
		if (!freecam) {
			sprint = cacheSprint;
			Minecraft.getInstance().setCameraEntity(Minecraft.getInstance().player);
			freecamEntity.discard();
			freecamEntity = null;
		}
		Minecraft.getInstance().player.sendSystemMessage(Component.literal("Freecam is now " + freecam));
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
		KeyMapping ts = KeyMappingHelper.registerKeyMapping(
				new KeyMapping(
						"Toggle Sprint",
						InputConstants.Type.KEYSYM,
						GLFW.GLFW_KEY_LEFT_CONTROL,
						CATEGORY
				));

		KeyMapping tf = KeyMappingHelper.registerKeyMapping(
				new KeyMapping(
						"Toggle Fps",
						InputConstants.Type.KEYSYM,
						GLFW.GLFW_KEY_F6,
						CATEGORY
				));

		KeyMapping freec = KeyMappingHelper.registerKeyMapping(
				new KeyMapping(
						"Toggle Freecam",
						InputConstants.Type.KEYSYM,
						GLFW.GLFW_KEY_F4,
						CATEGORY
				));

		ClientTickEvents.END_CLIENT_TICK.register((minecraft -> {
			var activePlayer = minecraft.player;

			Window window = Minecraft.getInstance().getWindow();

			if (activePlayer == null) return;

			while (ts.consumeClick()) {
				sprint = !sprint;
				minecraft.player.sendSystemMessage(Component.literal("Sprint is now on " + sprint));
			}

			while (tf.consumeClick()) {
				fps = !fps;
				minecraft.player.sendSystemMessage(Component.literal("Fps is now on " + fps));
			}

			while (freec.consumeClick()) {
				toggleFreecam();
			}

			if (fps) activePlayer.sendOverlayMessage(Component.literal(String.valueOf(minecraft.getFps())).withColor(0x0));

			if (sprint) activePlayer.setSprinting(true);

			if (freecam) {
				freecamEntity.setXRot(minecraft.player.getXRot());
				freecamEntity.setYRot(minecraft.player.getYRot());

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
			dispatcher.register(ClientCommands.literal("toggle.fps")
					.executes(context -> {
						fps = !fps;
						context.getSource().sendFeedback(Component.literal("FPS toggled to " + fps));
						return 1;
					})
			);

			dispatcher.register(ClientCommands.literal("toggle.sprint")
					.executes(context -> {
						sprint = !sprint;
						context.getSource().sendFeedback(Component.literal("Sprint toggled to " + sprint));
						return 1;
					})
			);

			dispatcher.register(ClientCommands.literal("enable.pvp")
					.executes(context -> {
						setPvp();
						context.getSource().sendFeedback(Component.literal("PVP options set"));
						return 1;
					})
			);

			dispatcher.register(ClientCommands.literal("reload")
					.executes(context -> {
						context.getSource().getClient().reloadResourcePacks();
						context.getSource().sendFeedback(Component.literal("Reloaded!"));
						return 1;
					})
			);

			dispatcher.register(ClientCommands.literal("freecam")
					.executes(context -> {
						toggleFreecam();
						return 1;
					})
			);

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
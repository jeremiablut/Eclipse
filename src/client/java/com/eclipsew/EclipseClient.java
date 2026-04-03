package com.eclipsew;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.glfw.GLFW;

public class EclipseClient implements ClientModInitializer {
	private boolean fps = false, sprint = true, freecam = false, cacheSprint, shown = false, digital = true;
	private Interaction freecamEntity;
	private final float distance = 0.75f;
	private String status, hoursToMinutesDigital, minutesToSecondsDigital, hoursToMinutesAnalog, minutesToSecondsAnalog, timer;
	private int ticks = 0, seconds, minutes = 0, hours = 0;

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

			if (fps) activePlayer.displayClientMessage(Component.literal(String.valueOf(minecraft.getFps())).withColor(0x00FFFF), true);

			if (sprint) activePlayer.setSprinting(true);

			if (freecam) {
				freecamEntity.setXRot(minecraft.player.getXRot());
				freecamEntity.setYRot(minecraft.player.getYRot());
				if (activePlayer.isShiftKeyDown()) {
					freecamEntity.setPos(freecamEntity.getX(), freecamEntity.getY() - 1, freecamEntity.getZ());
				}
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
			}
			{
				seconds = ticks / 20;

				if (status == "started") ticks++;

				else if (status == "restarted") {
					ticks = 0;
					minutes = 0;
					hours = 0;
				}
				genTimer();
				Component component = Component.nullToEmpty(timer)
						.copy()
						.withStyle(style -> style.withColor(0x0000AA)
								.withShadowColor(0x000000));
				if (shown) minecraft.player.displayClientMessage(component, true);
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
				if (!freecam) {
					freecamEntity = new Interaction(EntityType.INTERACTION, context.getSource().getPlayer().level());
					freecamEntity.setPos(context.getSource().getPlayer().getX(), context.getSource().getPlayer().getY() + 2, context.getSource().getPlayer().getZ());
					context.getSource().getWorld().addEntity(freecamEntity);
					context.getSource().getClient().setCameraEntity(freecamEntity);
					cacheSprint = sprint;
					sprint = false;
				}
				freecam = !freecam;
				if (!freecam) {
					sprint = cacheSprint;
					context.getSource().getClient().setCameraEntity(context.getSource().getPlayer());
					freecamEntity.discard();
				}
				context.getSource().sendFeedback(Component.literal("Freecam is now " + freecam));
				return 1;
			}));

			{
				dispatcher.register(ClientCommandManager.literal("timer.start").executes(context -> {
					status = "started";
					Component component = Component.nullToEmpty("Timer Started");
					context.getSource().getPlayer().displayClientMessage(component, false);
					return 1;
				}));

				dispatcher.register(ClientCommandManager.literal("timer.restart").executes(context -> {
					status = "restarted";
					Component component = Component.nullToEmpty("Timer was Restarted");
					context.getSource().getPlayer().displayClientMessage(component, false);
					return 1;
				}));

				dispatcher.register(ClientCommandManager.literal("timer.stop").executes(context -> {
					status = "stopped";
					Component component = Component.nullToEmpty("Timer was stopped");
					context.getSource().getPlayer().displayClientMessage(component, false);
					return 1;
				}));

				dispatcher.register(ClientCommandManager.literal("timer.toggle").executes(context -> {
					shown = !shown;
					Component component = Component.nullToEmpty("Timer was toggled");
					context.getSource().getPlayer().displayClientMessage(component, false);
					return 1;
				}));

				dispatcher.register(ClientCommandManager.literal("timer.digital").executes(context -> {
					digital = true;
					Component component = Component.nullToEmpty("Timer is digital");
					context.getSource().getPlayer().displayClientMessage(component, false);
					return 1;
				}));

				dispatcher.register(ClientCommandManager.literal("timer.analog").executes(context -> {
					digital = false;
					Player player = context.getSource().getPlayer();
					Component component = Component.nullToEmpty("Timer is analog");
					context.getSource().getPlayer().displayClientMessage(component, false);
					return 1;
				}));
			}
		});
	}
}

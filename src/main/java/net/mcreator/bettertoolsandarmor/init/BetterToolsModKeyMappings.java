
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.bettertoolsandarmor.network.StickToCeilingKeyMessage;
import net.mcreator.bettertoolsandarmor.network.FloatKeyMessage;
import net.mcreator.bettertoolsandarmor.network.DoubleJumpKeyMessage;
import net.mcreator.bettertoolsandarmor.network.AttributesViewerOpenMessage;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class BetterToolsModKeyMappings {
	public static final KeyMapping DOUBLE_JUMP_KEY = new KeyMapping("key.better_tools.double_jump_key", GLFW.GLFW_KEY_SPACE, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				BetterToolsMod.PACKET_HANDLER.sendToServer(new DoubleJumpKeyMessage(0, 0));
				DoubleJumpKeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping FLOAT_KEY = new KeyMapping("key.better_tools.float_key", GLFW.GLFW_KEY_LEFT_SHIFT, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				BetterToolsMod.PACKET_HANDLER.sendToServer(new FloatKeyMessage(0, 0));
				FloatKeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping ATTRIBUTES_VIEWER_OPEN = new KeyMapping("key.better_tools.attributes_viewer_open", GLFW.GLFW_KEY_PERIOD, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				BetterToolsMod.PACKET_HANDLER.sendToServer(new AttributesViewerOpenMessage(0, 0));
				AttributesViewerOpenMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping STICK_TO_CEILING_KEY = new KeyMapping("key.better_tools.stick_to_ceiling_key", GLFW.GLFW_KEY_SPACE, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				BetterToolsMod.PACKET_HANDLER.sendToServer(new StickToCeilingKeyMessage(0, 0));
				StickToCeilingKeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
				STICK_TO_CEILING_KEY_LASTPRESS = System.currentTimeMillis();
			} else if (isDownOld != isDown && !isDown) {
				int dt = (int) (System.currentTimeMillis() - STICK_TO_CEILING_KEY_LASTPRESS);
				BetterToolsMod.PACKET_HANDLER.sendToServer(new StickToCeilingKeyMessage(1, dt));
				StickToCeilingKeyMessage.pressAction(Minecraft.getInstance().player, 1, dt);
			}
			isDownOld = isDown;
		}
	};
	private static long STICK_TO_CEILING_KEY_LASTPRESS = 0;

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(DOUBLE_JUMP_KEY);
		event.register(FLOAT_KEY);
		event.register(ATTRIBUTES_VIEWER_OPEN);
		event.register(STICK_TO_CEILING_KEY);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				DOUBLE_JUMP_KEY.consumeClick();
				FLOAT_KEY.consumeClick();
				ATTRIBUTES_VIEWER_OPEN.consumeClick();
				STICK_TO_CEILING_KEY.consumeClick();
			}
		}
	}
}

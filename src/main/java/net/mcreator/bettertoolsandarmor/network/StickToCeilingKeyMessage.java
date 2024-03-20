
package net.mcreator.bettertoolsandarmor.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.bettertoolsandarmor.procedures.StickToCeilingKeyUpProcedure;
import net.mcreator.bettertoolsandarmor.procedures.StickToCeilingKeyDownProcedure;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class StickToCeilingKeyMessage {
	int type, pressedms;

	public StickToCeilingKeyMessage(int type, int pressedms) {
		this.type = type;
		this.pressedms = pressedms;
	}

	public StickToCeilingKeyMessage(FriendlyByteBuf buffer) {
		this.type = buffer.readInt();
		this.pressedms = buffer.readInt();
	}

	public static void buffer(StickToCeilingKeyMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.type);
		buffer.writeInt(message.pressedms);
	}

	public static void handler(StickToCeilingKeyMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			pressAction(context.getSender(), message.type, message.pressedms);
		});
		context.setPacketHandled(true);
	}

	public static void pressAction(Player entity, int type, int pressedms) {
		Level world = entity.level();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(entity.blockPosition()))
			return;
		if (type == 0) {

			StickToCeilingKeyDownProcedure.execute(entity);
		}
		if (type == 1) {

			StickToCeilingKeyUpProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		BetterToolsMod.addNetworkMessage(StickToCeilingKeyMessage.class, StickToCeilingKeyMessage::buffer, StickToCeilingKeyMessage::new, StickToCeilingKeyMessage::handler);
	}
}

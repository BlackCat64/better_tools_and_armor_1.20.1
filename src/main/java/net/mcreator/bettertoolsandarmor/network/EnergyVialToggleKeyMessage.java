
package net.mcreator.bettertoolsandarmor.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.bettertoolsandarmor.procedures.EnergyVialKeybindOpenGuiProcedure;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EnergyVialToggleKeyMessage {
	int type, pressedms;

	public EnergyVialToggleKeyMessage(int type, int pressedms) {
		this.type = type;
		this.pressedms = pressedms;
	}

	public EnergyVialToggleKeyMessage(FriendlyByteBuf buffer) {
		this.type = buffer.readInt();
		this.pressedms = buffer.readInt();
	}

	public static void buffer(EnergyVialToggleKeyMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.type);
		buffer.writeInt(message.pressedms);
	}

	public static void handler(EnergyVialToggleKeyMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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

			EnergyVialKeybindOpenGuiProcedure.execute(world, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		BetterToolsMod.addNetworkMessage(EnergyVialToggleKeyMessage.class, EnergyVialToggleKeyMessage::buffer, EnergyVialToggleKeyMessage::new, EnergyVialToggleKeyMessage::handler);
	}
}

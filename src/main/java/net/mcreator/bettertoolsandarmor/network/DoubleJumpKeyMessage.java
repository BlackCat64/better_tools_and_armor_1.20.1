package net.mcreator.bettertoolsandarmor.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;

import net.mcreator.bettertoolsandarmor.procedures.DoubleJumpKeyPressProcedure;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

@EventBusSubscriber
public record DoubleJumpKeyMessage(int eventType, int pressedms) implements CustomPacketPayload {
	public static final Type<DoubleJumpKeyMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(BetterToolsMod.MODID, "key_double_jump_key"));
	public static final StreamCodec<RegistryFriendlyByteBuf, DoubleJumpKeyMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, DoubleJumpKeyMessage message) -> {
		buffer.writeInt(message.eventType);
		buffer.writeInt(message.pressedms);
	}, (RegistryFriendlyByteBuf buffer) -> new DoubleJumpKeyMessage(buffer.readInt(), buffer.readInt()));

	@Override
	public Type<DoubleJumpKeyMessage> type() {
		return TYPE;
	}

	public static void handleData(final DoubleJumpKeyMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> {
				pressAction(context.player(), message.eventType, message.pressedms);
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
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

			DoubleJumpKeyPressProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		BetterToolsMod.addNetworkMessage(DoubleJumpKeyMessage.TYPE, DoubleJumpKeyMessage.STREAM_CODEC, DoubleJumpKeyMessage::handleData);
	}
}
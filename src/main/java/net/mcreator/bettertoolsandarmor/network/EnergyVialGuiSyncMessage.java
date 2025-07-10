package net.mcreator.bettertoolsandarmor.network;

import net.minecraftforge.network.NetworkEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.bettertoolsandarmor.world.inventory.EnergyVialMenuMenu;
import net.mcreator.bettertoolsandarmor.procedures.PlayerHasEnergyVialEquippedProcedure;

import java.util.function.Supplier;
import java.util.Map;

public class EnergyVialGuiSyncMessage {
	boolean helmetActive, chestplateActive, leggingsActive, bootsActive;

	public EnergyVialGuiSyncMessage(boolean helmet, boolean chestplate, boolean leggings, boolean boots) {
		this.helmetActive = helmet;
		this.chestplateActive = chestplate;
		this.leggingsActive = leggings;
		this.bootsActive = boots;
	}

	public static void buffer(EnergyVialGuiSyncMessage message, FriendlyByteBuf buffer) {
		buffer.writeBoolean(message.helmetActive);
		buffer.writeBoolean(message.chestplateActive);
		buffer.writeBoolean(message.leggingsActive);
		buffer.writeBoolean(message.bootsActive);
	}

	public static EnergyVialGuiSyncMessage decode(FriendlyByteBuf buffer) {
		return new EnergyVialGuiSyncMessage(buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean());
	}

	public static void handler(EnergyVialGuiSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		contextSupplier.get().enqueueWork(() -> {
			ServerPlayer player = contextSupplier.get().getSender();
			if (player == null)
				return;
			// only continue if the player is currently in the Energy Vial GUI
			if (!(player.containerMenu instanceof EnergyVialMenuMenu))
				return;

			// Extract vial from slot 1 of currently open GUI (the energy vial GUI)
			ItemStack vial = (player.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt) ? ((Slot) ((Map<?, ?>) _slt).get(1)).getItem() : ItemStack.EMPTY;
			CompoundTag tag = vial.getOrCreateTag();
			tag.putBoolean("helmet_active", message.helmetActive); // update tags of vial
			tag.putBoolean("chestplate_active", message.chestplateActive);
			tag.putBoolean("leggings_active", message.leggingsActive);
			tag.putBoolean("boots_active", message.bootsActive);

			// Update server-side player variable if needed
			if (PlayerHasEnergyVialEquippedProcedure.execute(player)) {
				CompoundTag _nbtTag = tag;
				player.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(cap -> {
					cap.energy_vial_to_update.setTag(_nbtTag.copy());
					cap.syncPlayerVariables(player);
				});
			} else {
				ItemStack _setval = vial;
				player.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(cap -> {
					cap.energy_vial_to_update = _setval;
					cap.syncPlayerVariables(player);
				});
			}
		});
		contextSupplier.get().setPacketHandled(true);
	}
}

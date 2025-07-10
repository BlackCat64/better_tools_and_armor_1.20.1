package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.client.gui.components.Checkbox;

import net.mcreator.bettertoolsandarmor.network.EnergyVialGuiSyncMessage;
import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

public class EnergyVialGuiUpdateProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		double energy = 0;
		double energy_gain = 0;
		double max_energy = 0;
		boolean helmet_active = false;
		boolean chestplate_active = false;
		boolean leggings_active = false;
		boolean boots_active = false;
		ItemStack fuel = ItemStack.EMPTY;
		ItemStack vial = ItemStack.EMPTY;
		fuel = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY);
		vial = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY);
		if (PlayerHasEnergyVialEquippedProcedure.execute(entity)) {
			energy = ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).energy_vial_to_update).getOrCreateTag().getDouble("energy");
		} else {
			energy = vial.getOrCreateTag().getDouble("energy");
		}
		max_energy = GetEnergyVialCapacityProcedure.execute(vial);
		if (energy < max_energy) {
			if (fuel.getItem() == BetterToolsModItems.ULTRA_ENRICHED_BLAZE_POWDER.get()) {
				energy_gain = 20000;
			} else if (fuel.getItem() == BetterToolsModItems.SUPER_ENRICHED_BLAZE_POWDER.get()) {
				energy_gain = 10000;
			} else if (fuel.getItem() == BetterToolsModItems.ENRICHED_BLAZE_POWDER.get()) {
				energy_gain = 5000;
			} else if (fuel.getItem() == Items.BLAZE_POWDER) {
				energy_gain = 1000;
			} else if (fuel.getItem() == Blocks.NETHER_WART.asItem()) {
				energy_gain = 500;
			}
			fuel.shrink(1);
			vial.getOrCreateTag().putDouble("energy", Math.min(energy + energy_gain, max_energy));
		}
		if (world.isClientSide()) {
			helmet_active = guistate.containsKey("checkbox:helmet_active") && ((Checkbox) guistate.get("checkbox:helmet_active")).selected();
			chestplate_active = guistate.containsKey("checkbox:chestplate_active") && ((Checkbox) guistate.get("checkbox:chestplate_active")).selected();
			leggings_active = guistate.containsKey("checkbox:leggings_active") && ((Checkbox) guistate.get("checkbox:leggings_active")).selected();
			boots_active = guistate.containsKey("checkbox:boots_active") && ((Checkbox) guistate.get("checkbox:boots_active")).selected();
			vial.getOrCreateTag().putBoolean("helmet_active", helmet_active);
			vial.getOrCreateTag().putBoolean("chestplate_active", chestplate_active);
			vial.getOrCreateTag().putBoolean("leggings_active", leggings_active);
			vial.getOrCreateTag().putBoolean("boots_active", boots_active);
			BetterToolsMod.PACKET_HANDLER.sendToServer(new EnergyVialGuiSyncMessage(helmet_active, chestplate_active, leggings_active, boots_active)); // Send data to server, to avoid desync issues
		}
		if (PlayerHasEnergyVialEquippedProcedure.execute(entity)) {
			{
				CompoundTag _nbtTag = vial.getTag();
				if (_nbtTag != null)
					((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).energy_vial_to_update).setTag(_nbtTag.copy());
			}
		} else {
			{
				ItemStack _setval = vial;
				entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.energy_vial_to_update = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import java.util.function.Supplier;
import java.util.Map;

public class EnergyVialGuiUpdateProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		ItemStack fuel = ItemStack.EMPTY;
		ItemStack vial = ItemStack.EMPTY;
		double energy = 0;
		double energy_gain = 0;
		boolean updated = false;
		fuel = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY);
		vial = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY);
		energy = vial.getOrCreateTag().getDouble("energy");
		if (energy < 18000) {
			if (fuel.getItem() == Items.BLAZE_POWDER) {
				energy_gain = 1000;
			} else if (fuel.getItem() == Blocks.NETHER_WART.asItem()) {
				energy_gain = 500;
			}
			fuel.shrink(1);
			vial.getOrCreateTag().putDouble("energy", Math.min(energy + energy_gain, 18000));
		}
		((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).energy_vial_to_update).getOrCreateTag().putDouble("energy",
				Math.min(vial.getOrCreateTag().getDouble("energy"), 18000));
	}
}

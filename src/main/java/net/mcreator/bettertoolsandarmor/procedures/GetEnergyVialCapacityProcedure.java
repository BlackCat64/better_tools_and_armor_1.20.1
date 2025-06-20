package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class GetEnergyVialCapacityProcedure {
	public static double execute(ItemStack itemstack) {
		if (itemstack.getItem() == BetterToolsModItems.ENERGY_VIAL.get()) {
			return 18000;
		} else if (itemstack.getItem() == BetterToolsModItems.EMERALD_ENERGY_VIAL.get()) {
			return 36000;
		} else if (itemstack.getItem() == BetterToolsModItems.NETHERITE_ENERGY_VIAL.get()) {
			return 72000;
		}
		return 0;
	}
}

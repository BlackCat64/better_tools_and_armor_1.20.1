package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;

public class EnergyVialEnergyValueProcedure {
	public static double execute(ItemStack itemstack) {
		return itemstack.getOrCreateTag().getDouble("energy");
	}
}

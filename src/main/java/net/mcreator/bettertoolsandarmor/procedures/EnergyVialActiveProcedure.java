package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;

public class EnergyVialActiveProcedure {
	public static double execute(ItemStack itemstack) {
		if (itemstack.getOrCreateTag().getBoolean("active")) {
			return 1;
		}
		return 0;
	}
}

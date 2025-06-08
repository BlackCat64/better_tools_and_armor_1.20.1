package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;

public class EnergyVialActiveProcedure {
	public static double execute(ItemStack itemstack) {
		if (itemstack.getOrCreateTag().getBoolean("helmet_active") || itemstack.getOrCreateTag().getBoolean("chestplate_active") || itemstack.getOrCreateTag().getBoolean("leggings_active") || itemstack.getOrCreateTag().getBoolean("boots_active")) {
			return 1;
		}
		return 0;
	}
}

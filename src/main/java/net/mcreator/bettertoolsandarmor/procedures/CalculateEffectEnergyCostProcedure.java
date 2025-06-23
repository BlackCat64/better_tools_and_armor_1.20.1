package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;

public class CalculateEffectEnergyCostProcedure {
	public static double execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return 0;
		if (EnergyVialActiveArmorPiecesProcedure.execute(entity, itemstack) == 4 && IsWearingGlassArmorFullSetProcedure.execute(entity)) {
			return GlassArmorEnergyCostProcedure.execute(entity);
		}
		return StandardEffectArmorEnergyCostProcedure.execute(entity, itemstack);
	}
}

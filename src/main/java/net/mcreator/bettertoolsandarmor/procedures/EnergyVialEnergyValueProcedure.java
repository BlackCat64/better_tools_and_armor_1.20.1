package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

public class EnergyVialEnergyValueProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		return ((LivingEntity) entity).getAttribute(BetterToolsModAttributes.EFFECTENERGYCOST.get()).getValue();
	}
}

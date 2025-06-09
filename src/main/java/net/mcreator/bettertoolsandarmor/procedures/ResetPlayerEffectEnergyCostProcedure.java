package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

public class ResetPlayerEffectEnergyCostProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		((LivingEntity) entity).getAttribute(BetterToolsModAttributes.EFFECTENERGYCOST.get()).setBaseValue(0);
	}
}

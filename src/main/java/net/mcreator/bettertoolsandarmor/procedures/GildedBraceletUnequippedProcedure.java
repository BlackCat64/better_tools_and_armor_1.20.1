package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class GildedBraceletUnequippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).removeModifier(UUID.fromString("690220ea-a287-43e1-8ba3-ddc831edb69c"));
		CharmCounterDecrementProcedure.execute(entity);
	}
}

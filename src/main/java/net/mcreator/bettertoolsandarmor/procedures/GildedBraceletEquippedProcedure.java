package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class GildedBraceletEquippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).hasModifier((new AttributeModifier(UUID.fromString("690220ea-a287-43e1-8ba3-ddc831edb69c"), "gilded_bracelet", 1, AttributeModifier.Operation.ADDITION)))))
			((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).addTransientModifier((new AttributeModifier(UUID.fromString("690220ea-a287-43e1-8ba3-ddc831edb69c"), "gilded_bracelet", 1, AttributeModifier.Operation.ADDITION)));
		CharmCounterIncrementProcedure.execute(entity);
	}
}

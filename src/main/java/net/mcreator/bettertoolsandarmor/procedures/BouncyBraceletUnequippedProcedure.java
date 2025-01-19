package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class BouncyBraceletUnequippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK).removeModifier(UUID.fromString("78907ff0-98e5-4349-b8a4-d43e27bbdd7e"));
		CharmCounterDecrementProcedure.execute(entity);
	}
}

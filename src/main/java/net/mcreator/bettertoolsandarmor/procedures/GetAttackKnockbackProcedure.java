package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class GetAttackKnockbackProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "" + new java.text.DecimalFormat("##.#").format(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK).getValue());
	}
}

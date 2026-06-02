package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

public class BouncyBraceletUnequippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity) {
			_entity.getAttribute(Attributes.ATTACK_KNOCKBACK).removeModifier(ResourceLocation.parse("better_tools:bouncy_bracelet"));
		}
	}
}
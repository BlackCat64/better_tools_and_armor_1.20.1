package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

public class CriticalHitEffectExpiresProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER)) {
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER).removeModifier(ResourceLocation.parse("better_tools:criticality_effect"));
			}
		}
	}
}
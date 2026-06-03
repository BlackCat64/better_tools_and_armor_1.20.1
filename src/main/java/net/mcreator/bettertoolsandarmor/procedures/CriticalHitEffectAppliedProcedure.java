package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

public class CriticalHitEffectAppliedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER)) {
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER).removeModifier(ResourceLocation.parse("better_tools:criticality_effect"));
			}
			if (entity instanceof LivingEntity _entity) {
				AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:criticality_effect"),
						(((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(BetterToolsModMobEffects.CRITICALITY) ? _livEnt.getEffect(BetterToolsModMobEffects.CRITICALITY).getAmplifier() : 0) + 1) * 0.25),
						AttributeModifier.Operation.ADD_VALUE);
				if (!_entity.getAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER).hasModifier(modifier.id())) {
					_entity.getAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER).addPermanentModifier(modifier);
				}
			}
		}
	}
}
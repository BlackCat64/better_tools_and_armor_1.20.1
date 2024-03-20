package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import java.util.UUID;

public class CriticalHitEffectAppliedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(((LivingEntity) entity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get())
				.hasModifier((new AttributeModifier(UUID.fromString("3ba804d9-a7f7-4d3d-b7c0-7e4220fc54f5"), "criticality_effect",
						(((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(BetterToolsModMobEffects.CRITICALITY.get()) ? _livEnt.getEffect(BetterToolsModMobEffects.CRITICALITY.get()).getAmplifier() : 0) + 1) * 0.25),
						AttributeModifier.Operation.ADDITION)))))
			((LivingEntity) entity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get())
					.addTransientModifier((new AttributeModifier(UUID.fromString("3ba804d9-a7f7-4d3d-b7c0-7e4220fc54f5"), "criticality_effect",
							(((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(BetterToolsModMobEffects.CRITICALITY.get()) ? _livEnt.getEffect(BetterToolsModMobEffects.CRITICALITY.get()).getAmplifier() : 0) + 1) * 0.25),
							AttributeModifier.Operation.ADDITION)));
	}
}

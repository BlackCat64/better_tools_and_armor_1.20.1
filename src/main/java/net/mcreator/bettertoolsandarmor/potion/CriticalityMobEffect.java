
package net.mcreator.bettertoolsandarmor.potion;

import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.bettertoolsandarmor.procedures.CriticalHitEffectExpiresProcedure;
import net.mcreator.bettertoolsandarmor.procedures.CriticalHitEffectAppliedProcedure;

public class CriticalityMobEffect extends MobEffect {
	public CriticalityMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -3975635);
	}

	@Override
	public String getDescriptionId() {
		return "effect.better_tools.criticality";
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		CriticalHitEffectAppliedProcedure.execute(entity);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		CriticalHitEffectExpiresProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}

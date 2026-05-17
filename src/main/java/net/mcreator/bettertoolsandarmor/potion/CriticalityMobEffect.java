
package net.mcreator.bettertoolsandarmor.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.bettertoolsandarmor.procedures.CriticalHitEffectAppliedProcedure;

public class CriticalityMobEffect extends MobEffect {
	public CriticalityMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -3975635);
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
		CriticalHitEffectAppliedProcedure.execute();
	}
}

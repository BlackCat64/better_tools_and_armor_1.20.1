
package net.mcreator.bettertoolsandarmor.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.bettertoolsandarmor.procedures.FrozenEffectParticlesProcedure;
import net.mcreator.bettertoolsandarmor.procedures.FrozenEffectAppliedProcedure;

public class FrozenMobEffect extends MobEffect {
	public FrozenMobEffect() {
		super(MobEffectCategory.HARMFUL, -5650177);
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
		FrozenEffectAppliedProcedure.execute(entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		FrozenEffectParticlesProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
		return super.applyEffectTick(entity, amplifier);
	}
}

package net.mcreator.bettertoolsandarmor.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.bettertoolsandarmor.procedures.TrappedInGroundStopMovementProcedure;
import net.mcreator.bettertoolsandarmor.procedures.PitfallEffectAppliedProcedure;
import net.mcreator.bettertoolsandarmor.procedures.DeleteEntityMudBlockDisplayProcedure;

public class PitfallMobEffect extends MobEffect {
	public PitfallMobEffect() {
		super(MobEffectCategory.HARMFUL, -12961731);
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
		PitfallEffectAppliedProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		TrappedInGroundStopMovementProcedure.execute(entity.level(), entity);
		return super.applyEffectTick(entity, amplifier);
	}

	@Override
	public void onMobRemoved(LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
		if (reason == Entity.RemovalReason.KILLED) {
			DeleteEntityMudBlockDisplayProcedure.execute(entity.level(), entity);
		}
	}
}
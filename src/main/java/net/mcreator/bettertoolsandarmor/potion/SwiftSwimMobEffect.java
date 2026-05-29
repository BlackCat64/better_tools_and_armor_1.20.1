package net.mcreator.bettertoolsandarmor.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.bettertoolsandarmor.procedures.SwiftSwimProcedureProcedure;

public class SwiftSwimMobEffect extends MobEffect {
	public SwiftSwimMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -9263976);
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
		SwiftSwimProcedureProcedure.execute();
	}
}
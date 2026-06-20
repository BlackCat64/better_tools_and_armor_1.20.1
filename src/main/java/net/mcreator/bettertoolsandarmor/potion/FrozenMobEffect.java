package net.mcreator.bettertoolsandarmor.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleOptions;

import net.mcreator.bettertoolsandarmor.procedures.FrozenEffectParticlesProcedure;
import net.mcreator.bettertoolsandarmor.procedures.FrozenEffectAppliedProcedure;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModParticleTypes;

public class FrozenMobEffect extends MobEffect {
	public FrozenMobEffect() {
		super(MobEffectCategory.HARMFUL, -5650177);
		this.withSoundOnAdded(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.trident.return")));
	}

	@Override
	public ParticleOptions createParticleOptions(MobEffectInstance mobEffectInstance) {
		return (SimpleParticleType) (BetterToolsModParticleTypes.ICE_PARTICLE.get());
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
		FrozenEffectAppliedProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		FrozenEffectParticlesProcedure.execute(entity.level(), entity);
		return super.applyEffectTick(entity, amplifier);
	}
}
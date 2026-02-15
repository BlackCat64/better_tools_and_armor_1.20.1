
package net.mcreator.bettertoolsandarmor.potion;

import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.bettertoolsandarmor.procedures.TrappedInGroundStopMovementProcedure;
import net.mcreator.bettertoolsandarmor.procedures.PitfallEffectExpiresProcedure;
import net.mcreator.bettertoolsandarmor.procedures.PitfallEffectAppliedProcedure;

public class PitfallMobEffect extends MobEffect {
	public PitfallMobEffect() {
		super(MobEffectCategory.HARMFUL, -12961731);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		PitfallEffectAppliedProcedure.execute(entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		TrappedInGroundStopMovementProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		PitfallEffectExpiresProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}

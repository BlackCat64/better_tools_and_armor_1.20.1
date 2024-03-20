
package net.mcreator.bettertoolsandarmor.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.bettertoolsandarmor.procedures.DoubleJumpProcedureProcedure;

public class DoubleJumpMobEffect extends MobEffect {
	public DoubleJumpMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -3355444);
	}

	@Override
	public String getDescriptionId() {
		return "effect.better_tools.double_jump";
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		DoubleJumpProcedureProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}

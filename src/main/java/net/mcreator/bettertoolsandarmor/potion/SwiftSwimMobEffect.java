
package net.mcreator.bettertoolsandarmor.potion;

import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.bettertoolsandarmor.procedures.SwiftSwimProcedureProcedure;
import net.mcreator.bettertoolsandarmor.procedures.SwiftSwimExpiresProcedure;

public class SwiftSwimMobEffect extends MobEffect {
	public SwiftSwimMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -9263976);
	}

	@Override
	public String getDescriptionId() {
		return "effect.better_tools.swift_swim";
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		SwiftSwimProcedureProcedure.execute(entity);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		SwiftSwimExpiresProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;

import java.util.UUID;

public class SwiftSwimProcedureProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(((LivingEntity) entity).getAttribute(ForgeMod.SWIM_SPEED.get())
				.hasModifier((new AttributeModifier(UUID.fromString("e4fc11f4-1915-4a88-bc02-f9feb8e5f0b9"), "swift_swim_effect",
						(((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(BetterToolsModMobEffects.SWIFT_SWIM.get()) ? _livEnt.getEffect(BetterToolsModMobEffects.SWIFT_SWIM.get()).getAmplifier() : 0) + 1) * 0.5),
						AttributeModifier.Operation.ADDITION)))))
			((LivingEntity) entity).getAttribute(ForgeMod.SWIM_SPEED.get())
					.addTransientModifier((new AttributeModifier(UUID.fromString("e4fc11f4-1915-4a88-bc02-f9feb8e5f0b9"), "swift_swim_effect",
							(((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(BetterToolsModMobEffects.SWIFT_SWIM.get()) ? _livEnt.getEffect(BetterToolsModMobEffects.SWIFT_SWIM.get()).getAmplifier() : 0) + 1) * 0.5),
							AttributeModifier.Operation.ADDITION)));
	}
}

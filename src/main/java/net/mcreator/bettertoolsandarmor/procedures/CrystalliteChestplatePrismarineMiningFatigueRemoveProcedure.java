package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;

public class CrystalliteChestplatePrismarineMiningFatigueRemoveProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(MobEffects.DIG_SLOWDOWN)) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.DIG_SLOWDOWN);
		}
	}
}

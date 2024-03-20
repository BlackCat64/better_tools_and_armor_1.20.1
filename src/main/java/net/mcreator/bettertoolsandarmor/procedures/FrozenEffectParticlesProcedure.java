package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModParticleTypes;

public class FrozenEffectParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world.dayTime() % 3 == 0) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (BetterToolsModParticleTypes.ICE_PARTICLE.get()), x, (y + entity.getBbHeight() / 2), z, 2, 0.33, 0.5, 0.33, 0.03);
		}
		entity.setTicksFrozen(135);
	}
}

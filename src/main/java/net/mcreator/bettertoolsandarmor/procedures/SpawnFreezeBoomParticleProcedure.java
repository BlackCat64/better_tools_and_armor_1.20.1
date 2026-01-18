package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModParticleTypes;

public class SpawnFreezeBoomParticleProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, double size) {
		if (size <= 2) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (BetterToolsModParticleTypes.FREEZE_BOOM_2.get()), x, y, z, 1, 0, 0, 0, 0);
		} else if (size <= 3) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (BetterToolsModParticleTypes.FREEZE_BOOM_3.get()), x, y, z, 1, 0, 0, 0, 0);
		} else if (size <= 4) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (BetterToolsModParticleTypes.FREEZE_BOOM_4.get()), x, y, z, 1, 0, 0, 0, 0);
		} else if (size <= 5) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (BetterToolsModParticleTypes.FREEZE_BOOM_5.get()), x, y, z, 1, 0, 0, 0, 0);
		} else {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (BetterToolsModParticleTypes.FREEZE_BOOM_6.get()), x, y, z, 1, 0, 0, 0, 0);
		}
	}
}

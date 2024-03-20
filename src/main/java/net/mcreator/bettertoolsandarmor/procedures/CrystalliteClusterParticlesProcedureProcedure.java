package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModParticleTypes;

public class CrystalliteClusterParticlesProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (BetterToolsModParticleTypes.CRYSTALLITE_SPARKLE.get()), x, y, z, 1, 3, 3, 3, 0.0025);
	}
}

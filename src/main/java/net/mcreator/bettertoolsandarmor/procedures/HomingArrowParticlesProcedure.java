package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModParticleTypes;

public class HomingArrowParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, double x2, double y2, double z2) {
		double distance = 0;
		double dx = 0;
		double dy = 0;
		double dz = 0;
		double i = 0;
		distance = GetDistanceBetweenPointsProcedure.execute(x, y, z, x2, y2, z2) * 2;
		dx = (x2 - x) / distance;
		dy = (y2 - y) / distance;
		dz = (z2 - z) / distance;
		if (distance > 0) {
			for (int index0 = 0; index0 < (int) Math.floor(distance); index0++) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (BetterToolsModParticleTypes.ARROW_HOMING_PARTICLE.get()), (x + i * dx), (y + i * dy), (z + i * dz), 3, 0.02, 0.02, 0.02, 0.0025);
				i = i + 1;
			}
		}
	}
}

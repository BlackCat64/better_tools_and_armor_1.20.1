package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;

public class FindNextSafeLocationAboveProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z, double max_y) {
		double i_y = 0;
		i_y = y;
		while (i_y <= max_y) {
			if (IsLocationSafeProcedure.execute(world, x, i_y, z)) {
				return i_y;
			}
			i_y = i_y + 1;
		}
		return -1000;
	}
}

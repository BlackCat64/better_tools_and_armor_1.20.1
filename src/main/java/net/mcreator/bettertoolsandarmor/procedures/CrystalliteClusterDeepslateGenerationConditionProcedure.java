package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;

public class CrystalliteClusterDeepslateGenerationConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (y >= -48 && y <= 0) {
			CrystalliteClusterGeneratedProcedure.execute(world, x, y, z);
			return true;
		}
		return false;
	}
}

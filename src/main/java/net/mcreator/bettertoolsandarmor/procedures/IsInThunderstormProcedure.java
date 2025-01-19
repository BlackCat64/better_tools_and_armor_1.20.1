package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class IsInThunderstormProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return world.getLevelData().isThundering() && world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z));
	}
}

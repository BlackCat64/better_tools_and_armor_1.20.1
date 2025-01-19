package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BlueMushroomSpaceCheckProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		sx = -5;
		found = false;
		for (int index0 = 0; index0 < 10; index0++) {
			sy = 0;
			for (int index1 = 0; index1 < 9; index1++) {
				sz = -5;
				for (int index2 = 0; index2 < 10; index2++) {
					if (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz)).canOcclude()) {
						found = true;
					}
					sz = sz + 1;
				}
				sy = sy + 1;
			}
			sx = sx + 1;
		}
		return !found;
	}
}

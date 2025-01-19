package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class IsPlayerInTheDarkProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return false;
		double time = 0;
		time = world.dayTime() % 24000;
		if (time % 2 == 0) {
			if (!world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z)) && world.getMaxLocalRawBrightness(BlockPos.containing(x, y, z)) < 4) {
				return true;
			} else if (time >= 13000 || !((entity.level().dimension()) == Level.OVERWORLD)) {
				if (world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z))) {
					return true;
				} else {
					if (world.getMaxLocalRawBrightness(BlockPos.containing(x, y, z)) < 4) {
						return true;
					} else {
						return false;
					}
				}
			}
			return false;
		}
		return false;
	}
}

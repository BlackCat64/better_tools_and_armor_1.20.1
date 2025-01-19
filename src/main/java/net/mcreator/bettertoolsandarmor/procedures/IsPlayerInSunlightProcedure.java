package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class IsPlayerInSunlightProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return false;
		if ((entity.level().dimension()) == Level.OVERWORLD) {
			if (world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z))) {
				if (!(world.getLevelData().isRaining() || world.getLevelData().isThundering())) {
					if (world.dayTime() % 24000 >= 0 && world.dayTime() % 24000 <= 12000) {
						return true;
					}
				}
			}
		}
		return false;
	}
}

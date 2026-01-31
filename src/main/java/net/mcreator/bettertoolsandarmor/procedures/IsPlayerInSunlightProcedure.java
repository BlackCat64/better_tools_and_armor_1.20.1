package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class IsPlayerInSunlightProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return false;
		return (entity.level().dimension()) == Level.OVERWORLD && world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z)) && !world.getLevelData().isRaining() && world.dayTime() % 24000 >= 0 && world.dayTime() % 24000 <= 12000;
	}
}

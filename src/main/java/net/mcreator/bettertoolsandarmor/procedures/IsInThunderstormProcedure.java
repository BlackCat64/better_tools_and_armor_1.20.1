package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class IsInThunderstormProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return false;
		return entity.isInWaterRainOrBubble() && world.getLevelData().isThundering() && world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z));
	}
}

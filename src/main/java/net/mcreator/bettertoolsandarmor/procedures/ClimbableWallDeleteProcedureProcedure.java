package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class ClimbableWallDeleteProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		BetterToolsMod.queueServerWork(20, () -> {
			world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
		});
	}
}

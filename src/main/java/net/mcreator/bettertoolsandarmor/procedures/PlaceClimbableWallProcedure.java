package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModBlocks;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class PlaceClimbableWallProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isEmptyBlock(BlockPos.containing(x, y, z))) {
			world.setBlock(BlockPos.containing(x, y, z), BetterToolsModBlocks.CLIMBABLE_WALL.get().defaultBlockState(), 3);
			BetterToolsMod.queueServerWork(40, () -> {
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			});
		}
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class IsLocationSafeProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return world.getBlockState(BlockPos.containing(x, y - 1, z)).isFaceSturdy(world, BlockPos.containing(x, y - 1, z), Direction.UP) && !world.getBlockState(BlockPos.containing(x, y, z)).canOcclude()
				&& !(BuiltInRegistries.BLOCK.getKey((world.getFluidState(BlockPos.containing(x, y, z)).createLegacyBlock()).getBlock()).toString()).equals("minecraft:lava") && !world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude()
				&& !(BuiltInRegistries.BLOCK.getKey((world.getFluidState(BlockPos.containing(x, y + 1, z)).createLegacyBlock()).getBlock()).toString()).equals("minecraft:lava");
	}
}
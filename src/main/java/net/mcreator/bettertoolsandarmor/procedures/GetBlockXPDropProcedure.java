package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModBlocks;

public class GetBlockXPDropProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return 0;
		if (blockstate.getBlock() == BetterToolsModBlocks.RUBY_ORE.get() || blockstate.getBlock() == BetterToolsModBlocks.DEEPSLATE_RUBY_ORE.get()) {
			return Mth.nextInt(RandomSource.create(), 6, 10);
		} else if (blockstate.getBlock() == BetterToolsModBlocks.RANDOM_ORE.get() || blockstate.getBlock() == BetterToolsModBlocks.DEEPSLATE_RANDOM_ORE.get()) {
			return Mth.nextInt(RandomSource.create(), 1, 10);
		} else if (blockstate.getBlock() == BetterToolsModBlocks.SAPPHIRE_ORE.get() || blockstate.getBlock() == BetterToolsModBlocks.TOPAZ_ORE.get()) {
			return Mth.nextInt(RandomSource.create(), 2, 5);
		} else if (blockstate.getBlock() == BetterToolsModBlocks.NETHER_DIAMOND_ORE.get()) {
			return Mth.nextInt(RandomSource.create(), 3, 7);
		}
		return GetVanillaBlockXPDropProcedure.execute(world, x, y, z, blockstate, entity);
	}
}

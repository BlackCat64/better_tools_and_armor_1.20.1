package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

public class PurpleMushroomBlockParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!(world instanceof Level _level0 && _level0.hasNeighborSignal(BlockPos.containing(x, y, z)))) {
			if (world.isEmptyBlock(BlockPos.containing(x, y + 1, z)) || !world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude() || (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() instanceof SimpleWaterloggedBlock
					|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.MOSS_CARPET || (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BlockTags.create(new ResourceLocation("minecraft:wool_carpets")))
					|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BlockTags.create(new ResourceLocation("minecraft:doors")))) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.DRAGON_BREATH, (x + 0.5), (y + 1.25), (z + 0.5), 1, 0.6, 0.1, 0.6, 0.0001);
			}
		}
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class EarthCircletOreIlluminationProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double x_offset = 0;
		double y_offset = 0;
		double z_offset = 0;
		if (world.dayTime() % 5 == 0) {
			sx = -16;
			for (int index0 = 0; index0 < 32; index0++) {
				sy = -16;
				for (int index1 = 0; index1 < 32; index1++) {
					sz = -16;
					for (int index2 = 0; index2 < 32; index2++) {
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BlockTags.create(new ResourceLocation("forge:ores")))) {
							x_offset = -1;
							for (int index3 = 0; index3 < 2; index3++) {
								if (world.isEmptyBlock(BlockPos.containing(x + sx + x_offset, y + sy, z + sz))) {
									world.setBlock(BlockPos.containing(x + sx + x_offset, y + sy, z + sz), Blocks.LIGHT.defaultBlockState(), 3);
									{
										int _value = 3;
										BlockPos _pos = BlockPos.containing(x + sx + x_offset, y + sy, z + sz);
										BlockState _bs = world.getBlockState(_pos);
										if (_bs.getBlock().getStateDefinition().getProperty("level") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
											world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
									}
								}
								x_offset = 1;
							}
							y_offset = -1;
							for (int index4 = 0; index4 < 2; index4++) {
								if (world.isEmptyBlock(BlockPos.containing(x + sx, y + sy + y_offset, z + sz))) {
									world.setBlock(BlockPos.containing(x + sx, y + sy + y_offset, z + sz), Blocks.LIGHT.defaultBlockState(), 3);
									{
										int _value = 3;
										BlockPos _pos = BlockPos.containing(x + sx, y + sy + y_offset, z + sz);
										BlockState _bs = world.getBlockState(_pos);
										if (_bs.getBlock().getStateDefinition().getProperty("level") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
											world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
									}
								}
								y_offset = 1;
							}
							z_offset = -1;
							for (int index5 = 0; index5 < 2; index5++) {
								if (world.isEmptyBlock(BlockPos.containing(x + sx, y + sy, z + sz + z_offset))) {
									world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz + z_offset), Blocks.LIGHT.defaultBlockState(), 3);
									{
										int _value = 3;
										BlockPos _pos = BlockPos.containing(x + sx, y + sy, z + sz + z_offset);
										BlockState _bs = world.getBlockState(_pos);
										if (_bs.getBlock().getStateDefinition().getProperty("level") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
											world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
									}
								}
								z_offset = 1;
							}
						}
						sz = sz + 1;
					}
					sy = sy + 1;
				}
				sx = sx + 1;
			}
		}
	}
}

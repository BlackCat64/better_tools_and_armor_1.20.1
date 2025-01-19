package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class CrystalliteShovelSculkProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double x_disp = 0;
		double y_disp = 0;
		double z_disp = 0;
		x_disp = 0;
		y_disp = 0;
		z_disp = 0;
		if (!entity.isShiftKeyDown()) {
			if (blockstate.is(BlockTags.create(new ResourceLocation("minecraft:mineable/shovel")))) {
				if (entity.getXRot() > 40 || entity.getXRot() < -40) {
					x_disp = -1;
					for (int index0 = 0; index0 < 3; index0++) {
						z_disp = -1;
						for (int index1 = 0; index1 < 3; index1++) {
							if (!(x_disp == 0 && y_disp == 0 && z_disp == 0)) {
								if ((world.getBlockState(BlockPos.containing(x + x_disp, y, z + z_disp))).is(BlockTags.create(new ResourceLocation("minecraft:mineable/shovel")))) {
									{
										BlockPos _pos = BlockPos.containing(x + x_disp, y, z + z_disp);
										Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), null);
										world.destroyBlock(_pos, false);
									}
									{
										ItemStack _ist = itemstack;
										if (_ist.hurt(1, RandomSource.create(), null)) {
											_ist.shrink(1);
											_ist.setDamageValue(0);
										}
									}
								}
							}
							z_disp = z_disp + 1;
						}
						x_disp = x_disp + 1;
					}
				} else if ((entity.getDirection()) == Direction.NORTH || (entity.getDirection()) == Direction.SOUTH) {
					x_disp = -1;
					for (int index2 = 0; index2 < 3; index2++) {
						y_disp = -1;
						for (int index3 = 0; index3 < 3; index3++) {
							if (!(x_disp == 0 && y_disp == 0 && z_disp == 0)) {
								if ((world.getBlockState(BlockPos.containing(x + x_disp, y + y_disp, z))).is(BlockTags.create(new ResourceLocation("minecraft:mineable/shovel")))) {
									{
										BlockPos _pos = BlockPos.containing(x + x_disp, y + y_disp, z);
										Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), null);
										world.destroyBlock(_pos, false);
									}
									{
										ItemStack _ist = itemstack;
										if (_ist.hurt(1, RandomSource.create(), null)) {
											_ist.shrink(1);
											_ist.setDamageValue(0);
										}
									}
								}
							}
							y_disp = y_disp + 1;
						}
						x_disp = x_disp + 1;
					}
				} else if ((entity.getDirection()) == Direction.WEST || (entity.getDirection()) == Direction.EAST) {
					z_disp = -1;
					for (int index4 = 0; index4 < 3; index4++) {
						y_disp = -1;
						for (int index5 = 0; index5 < 3; index5++) {
							if (!(x_disp == 0 && y_disp == 0 && z_disp == 0)) {
								if ((world.getBlockState(BlockPos.containing(x, y + y_disp, z + z_disp))).is(BlockTags.create(new ResourceLocation("minecraft:mineable/shovel")))) {
									{
										BlockPos _pos = BlockPos.containing(x, y + y_disp, z + z_disp);
										Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), null);
										world.destroyBlock(_pos, false);
									}
									{
										ItemStack _ist = itemstack;
										if (_ist.hurt(1, RandomSource.create(), null)) {
											_ist.shrink(1);
											_ist.setDamageValue(0);
										}
									}
								}
							}
							y_disp = y_disp + 1;
						}
						z_disp = z_disp + 1;
					}
				}
			}
		}
	}
}

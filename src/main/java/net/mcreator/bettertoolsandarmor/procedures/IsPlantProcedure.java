package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.IPlantable;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class IsPlantProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return (world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(new ResourceLocation("better_tools:plants")))
				|| world.getBlockState(BlockPos.containing(x, y, z)).getBlock() instanceof IPlantable _plant2 && _plant2.getPlantType(world, BlockPos.containing(x, y, z)) == PlantType.CROP
				|| world.getBlockState(BlockPos.containing(x, y, z)).getBlock() instanceof IPlantable _plant3 && _plant3.getPlantType(world, BlockPos.containing(x, y, z)) == PlantType.PLAINS
				|| world.getBlockState(BlockPos.containing(x, y, z)).getBlock() instanceof IPlantable _plant4 && _plant4.getPlantType(world, BlockPos.containing(x, y, z)) == PlantType.DESERT
				|| world.getBlockState(BlockPos.containing(x, y, z)).getBlock() instanceof IPlantable _plant5 && _plant5.getPlantType(world, BlockPos.containing(x, y, z)) == PlantType.BEACH
				|| world.getBlockState(BlockPos.containing(x, y, z)).getBlock() instanceof IPlantable _plant6 && _plant6.getPlantType(world, BlockPos.containing(x, y, z)) == PlantType.CAVE
				|| world.getBlockState(BlockPos.containing(x, y, z)).getBlock() instanceof IPlantable _plant7 && _plant7.getPlantType(world, BlockPos.containing(x, y, z)) == PlantType.WATER
				|| world.getBlockState(BlockPos.containing(x, y, z)).getBlock() instanceof IPlantable _plant8 && _plant8.getPlantType(world, BlockPos.containing(x, y, z)) == PlantType.NETHER;
	}
}

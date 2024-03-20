package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class BlueMushroomGrowProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double random_direction = 0;
		if (Math.random() < 0.25) {
			random_direction = Math.random();
			if (random_direction < 0.25) {
				if (world instanceof ServerLevel _serverworld) {
					StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("better_tools", "grown_blue_mushroom"));
					if (template != null) {
						template.placeInWorld(_serverworld, BlockPos.containing(x - 5, y, z - 2), BlockPos.containing(x - 5, y, z - 2), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false),
								_serverworld.random, 3);
					}
				}
			} else if (random_direction < 0.5) {
				if (world instanceof ServerLevel _serverworld) {
					StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("better_tools", "grown_blue_mushroom"));
					if (template != null) {
						template.placeInWorld(_serverworld, BlockPos.containing(x + 2, y, z - 5), BlockPos.containing(x + 2, y, z - 5), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
								_serverworld.random, 3);
					}
				}
			} else if (random_direction < 0.75) {
				if (world instanceof ServerLevel _serverworld) {
					StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("better_tools", "grown_blue_mushroom"));
					if (template != null) {
						template.placeInWorld(_serverworld, BlockPos.containing(x + 5, y, z + 2), BlockPos.containing(x + 5, y, z + 2), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_180).setMirror(Mirror.NONE).setIgnoreEntities(false),
								_serverworld.random, 3);
					}
				}
			} else {
				if (world instanceof ServerLevel _serverworld) {
					StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("better_tools", "grown_blue_mushroom"));
					if (template != null) {
						template.placeInWorld(_serverworld, BlockPos.containing(x - 2, y, z + 5), BlockPos.containing(x - 2, y, z + 5),
								new StructurePlaceSettings().setRotation(Rotation.COUNTERCLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random, 3);
					}
				}
			}
		}
	}
}

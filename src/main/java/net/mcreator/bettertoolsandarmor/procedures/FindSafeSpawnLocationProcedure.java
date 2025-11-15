package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.LevelAccessor;

public class FindSafeSpawnLocationProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z, String dimension) {
		if (dimension == null)
			return 0;
		double void_height = 0;
		double safe_y = 0;
		double world_surface = 0;
		if ((dimension).equals("minecraft:overworld")) {
			void_height = -64;
		} else {
			void_height = 0;
		}
		world_surface = world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) x, (int) z);
		if ((dimension).equals("minecraft:the_nether") && world_surface >= 127) {
			world_surface = 120;
		}
		safe_y = FindNextSafeLocationAboveProcedure.execute(world, x, Math.max(y, void_height), z, world_surface + 1);
		if (safe_y < void_height) {
			safe_y = FindNextSafeLocationBelowProcedure.execute(world, x, y, z, void_height);
		}
		return safe_y;
	}
}

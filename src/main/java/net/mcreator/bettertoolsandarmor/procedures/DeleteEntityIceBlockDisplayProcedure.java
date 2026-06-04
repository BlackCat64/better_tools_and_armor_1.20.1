package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Display;
import net.minecraft.server.level.ServerLevel;

import java.util.UUID;

public class DeleteEntityIceBlockDisplayProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		Entity display = null;
		display = world instanceof ServerLevel _level1 ? getEntityFromUUID(_level1, (entity.getPersistentData().getString("frozen_block_display"))) : null;
		if (display instanceof Display.BlockDisplay) {
			if (!display.level().isClientSide())
				display.discard();
		}
	}

	private static Entity getEntityFromUUID(ServerLevel level, String uuid) {
		try {
			return level.getEntity(UUID.fromString(uuid));
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
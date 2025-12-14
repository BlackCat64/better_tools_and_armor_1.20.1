package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.Entity;

public class GetEntitySpeedProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		return Math.sqrt(Math.pow(entity.getDeltaMovement().x(), 2) + Math.pow(entity.getDeltaMovement().y(), 2) + Math.pow(entity.getDeltaMovement().z(), 2));
	}
}

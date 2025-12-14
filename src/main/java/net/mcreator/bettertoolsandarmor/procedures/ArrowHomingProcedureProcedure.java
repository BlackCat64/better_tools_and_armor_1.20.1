package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

public class ArrowHomingProcedureProcedure {
	public static void execute(LevelAccessor world, Entity arrow, Entity entity) {
		if (arrow == null || entity == null)
			return;
		double arrow_speed = 0;
		double distance = 0;
		double new_vx = 0;
		double new_vy = 0;
		double new_vz = 0;
		if (!world.isClientSide()) {
			arrow_speed = GetEntitySpeedProcedure.execute(arrow);
			distance = GetDistanceBetweenPointsProcedure.execute(arrow.getX(), arrow.getY(), arrow.getZ(), entity.getX(), entity.getY(), entity.getZ());
			new_vx = ((entity.getX() - arrow.getX()) / distance) * arrow_speed;
			new_vy = (((entity.getY() + entity.getBbHeight() / 2) - arrow.getY()) / distance) * arrow_speed;
			new_vz = ((entity.getZ() - arrow.getZ()) / distance) * arrow_speed;
			arrow.setDeltaMovement(new Vec3(new_vx, new_vy, new_vz));
		}
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class ApplyKnockbackProcedure {
	public static void execute(double x, double z, Entity entity, double power) {
		if (entity == null)
			return;
		double knockback_power = 0;
		double y_velocity = 0;
		double x_diff = 0;
		double z_diff = 0;
		double distance = 0;
		double z_velocity = 0;
		double x_velocity = 0;
		if (entity instanceof LivingEntity) {
			knockback_power = Math.max(power * (1 - ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.KNOCKBACK_RESISTANCE).getValue()), 0);
			x_diff = entity.getX() - x;
			z_diff = entity.getZ() - z;
			distance = Math.sqrt(Math.pow(x_diff, 2) + Math.pow(z_diff, 2));
			x_velocity = (x_diff / distance) * knockback_power;
			y_velocity = 0.5 * knockback_power;
			z_velocity = (z_diff / distance) * knockback_power;
			if (knockback_power > 0) {
				entity.setDeltaMovement(new Vec3(x_velocity, y_velocity, z_velocity));
			}
		}
	}
}

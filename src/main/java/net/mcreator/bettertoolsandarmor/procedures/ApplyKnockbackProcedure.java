package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class ApplyKnockbackProcedure {
	public static void execute(Entity entity, double power, Vec3 direction) {
		if (entity == null || direction == null)
			return;
		double resistance = 0;
		Vec3 dir = Vec3.ZERO;
		Vec3 knockback = Vec3.ZERO;
		if (entity instanceof LivingEntity && direction.length() > 0.000001) {
			dir = direction.normalize();
			resistance = Math.max(1 - (entity instanceof LivingEntity _livingEntity3 && _livingEntity3.getAttributes().hasAttribute(Attributes.KNOCKBACK_RESISTANCE) ? _livingEntity3.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue() : 0), 0);
			knockback = dir.scale((power * resistance));
			entity.push(knockback.x, 0.1, knockback.z);
		}
	}
}
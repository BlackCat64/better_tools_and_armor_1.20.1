package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class ToughNecklaceUnequippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.KNOCKBACK_RESISTANCE).removeModifier(UUID.fromString("a7a469f5-b02b-41ff-906d-e18993d9030c"));
		CharmCounterDecrementProcedure.execute(entity);
	}
}

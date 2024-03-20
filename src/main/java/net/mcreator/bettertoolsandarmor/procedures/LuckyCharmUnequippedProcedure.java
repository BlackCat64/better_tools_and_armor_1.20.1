package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class LuckyCharmUnequippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).removeModifier(UUID.fromString("20174daf-d532-4c70-b15d-a32ee360384d"));
		CharmCounterDecrementProcedure.execute(entity);
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class HeartCharmEquippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
				.hasModifier((new AttributeModifier(UUID.fromString("75687371-5b30-4f12-a36f-c49855cd20f3"), "heart_charm", 2, AttributeModifier.Operation.ADDITION)))))
			((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
					.addTransientModifier((new AttributeModifier(UUID.fromString("75687371-5b30-4f12-a36f-c49855cd20f3"), "heart_charm", 2, AttributeModifier.Operation.ADDITION)));
		CharmCounterIncrementProcedure.execute(entity);
	}
}

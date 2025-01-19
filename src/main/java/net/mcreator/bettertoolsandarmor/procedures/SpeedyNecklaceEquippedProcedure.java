package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class SpeedyNecklaceEquippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED)
				.hasModifier((new AttributeModifier(UUID.fromString("273bf762-4a3d-4feb-a00f-0cb3a0866f5f"), "speedy_necklace", 0.01, AttributeModifier.Operation.ADDITION)))))
			((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED)
					.addTransientModifier((new AttributeModifier(UUID.fromString("273bf762-4a3d-4feb-a00f-0cb3a0866f5f"), "speedy_necklace", 0.01, AttributeModifier.Operation.ADDITION)));
		CharmCounterIncrementProcedure.execute(entity);
	}
}

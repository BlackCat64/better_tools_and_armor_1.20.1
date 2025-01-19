package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class LuckyCharmEquippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK)
				.hasModifier((new AttributeModifier(UUID.fromString("20174daf-d532-4c70-b15d-a32ee360384d"), "lucky_charm", 1, AttributeModifier.Operation.ADDITION)))))
			((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK)
					.addTransientModifier((new AttributeModifier(UUID.fromString("20174daf-d532-4c70-b15d-a32ee360384d"), "lucky_charm", 1, AttributeModifier.Operation.ADDITION)));
		CharmCounterIncrementProcedure.execute(entity);
	}
}

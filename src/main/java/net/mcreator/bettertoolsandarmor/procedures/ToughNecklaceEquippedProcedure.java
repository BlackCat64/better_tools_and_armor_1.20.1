package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class ToughNecklaceEquippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.KNOCKBACK_RESISTANCE)
				.hasModifier((new AttributeModifier(UUID.fromString("a7a469f5-b02b-41ff-906d-e18993d9030c"), "tough_necklace", 0.15, AttributeModifier.Operation.ADDITION)))))
			((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.KNOCKBACK_RESISTANCE)
					.addTransientModifier((new AttributeModifier(UUID.fromString("a7a469f5-b02b-41ff-906d-e18993d9030c"), "tough_necklace", 0.15, AttributeModifier.Operation.ADDITION)));
		CharmCounterIncrementProcedure.execute(entity);
	}
}

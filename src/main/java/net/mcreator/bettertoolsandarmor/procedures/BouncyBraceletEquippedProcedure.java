package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class BouncyBraceletEquippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK)
				.hasModifier((new AttributeModifier(UUID.fromString("78907ff0-98e5-4349-b8a4-d43e27bbdd7e"), "bouncy_bracelet", 2, AttributeModifier.Operation.ADDITION)))))
			((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK)
					.addTransientModifier((new AttributeModifier(UUID.fromString("78907ff0-98e5-4349-b8a4-d43e27bbdd7e"), "bouncy_bracelet", 2, AttributeModifier.Operation.ADDITION)));
		CharmCounterIncrementProcedure.execute(entity);
	}
}

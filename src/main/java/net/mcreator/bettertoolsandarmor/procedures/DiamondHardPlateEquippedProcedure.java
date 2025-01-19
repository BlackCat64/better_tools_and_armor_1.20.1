package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class DiamondHardPlateEquippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR)
				.hasModifier((new AttributeModifier(UUID.fromString("73f6614b-58c6-409d-953c-c2eb877d2e91"), "diamond_hard_plate_armor", 2, AttributeModifier.Operation.ADDITION)))))
			((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR)
					.addTransientModifier((new AttributeModifier(UUID.fromString("73f6614b-58c6-409d-953c-c2eb877d2e91"), "diamond_hard_plate_armor", 2, AttributeModifier.Operation.ADDITION)));
		if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS)
				.hasModifier((new AttributeModifier(UUID.fromString("7746ac65-5e7c-48bd-a21b-5de649aa31d0"), "diamond_hard_plate_toughness", 2, AttributeModifier.Operation.ADDITION)))))
			((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS)
					.addTransientModifier((new AttributeModifier(UUID.fromString("7746ac65-5e7c-48bd-a21b-5de649aa31d0"), "diamond_hard_plate_toughness", 2, AttributeModifier.Operation.ADDITION)));
		CharmCounterIncrementProcedure.execute(entity);
	}
}

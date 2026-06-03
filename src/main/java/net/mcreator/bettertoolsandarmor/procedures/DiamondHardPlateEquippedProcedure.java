package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

public class DiamondHardPlateEquippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity) {
			AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:diamond_hard_plate_armor"), 2, AttributeModifier.Operation.ADD_VALUE);
			if (!_entity.getAttribute(Attributes.ARMOR).hasModifier(modifier.id())) {
				_entity.getAttribute(Attributes.ARMOR).addPermanentModifier(modifier);
			}
		}
		if (entity instanceof LivingEntity _entity) {
			AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:diamond_hard_plate_toughness"), 2, AttributeModifier.Operation.ADD_VALUE);
			if (!_entity.getAttribute(Attributes.ARMOR_TOUGHNESS).hasModifier(modifier.id())) {
				_entity.getAttribute(Attributes.ARMOR_TOUGHNESS).addPermanentModifier(modifier);
			}
		}
	}
}
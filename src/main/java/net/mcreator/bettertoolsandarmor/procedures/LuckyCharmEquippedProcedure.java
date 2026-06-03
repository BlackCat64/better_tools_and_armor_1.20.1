package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

public class LuckyCharmEquippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(Attributes.LUCK)) {
			if (entity instanceof LivingEntity _entity) {
				AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:lucky_charm"), 1, AttributeModifier.Operation.ADD_VALUE);
				if (!_entity.getAttribute(Attributes.LUCK).hasModifier(modifier.id())) {
					_entity.getAttribute(Attributes.LUCK).addPermanentModifier(modifier);
				}
			}
		}
	}
}
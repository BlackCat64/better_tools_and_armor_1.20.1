package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class DiamondHardPlateUnequippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR).removeModifier(UUID.fromString("73f6614b-58c6-409d-953c-c2eb877d2e91"));
		((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS).removeModifier(UUID.fromString("7746ac65-5e7c-48bd-a21b-5de649aa31d0"));
		CharmCounterDecrementProcedure.execute(entity);
	}
}

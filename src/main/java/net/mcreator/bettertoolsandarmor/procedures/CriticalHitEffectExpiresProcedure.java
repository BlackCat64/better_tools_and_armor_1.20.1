package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import java.util.UUID;

public class CriticalHitEffectExpiresProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		((LivingEntity) entity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get()).removeModifier((new AttributeModifier(UUID.fromString("3ba804d9-a7f7-4d3d-b7c0-7e4220fc54f5"), "", 0, AttributeModifier.Operation.ADDITION)));
	}
}

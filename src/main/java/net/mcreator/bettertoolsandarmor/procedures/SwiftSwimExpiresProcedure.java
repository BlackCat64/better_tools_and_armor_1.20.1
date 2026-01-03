package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class SwiftSwimExpiresProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		((LivingEntity) entity).getAttribute(ForgeMod.SWIM_SPEED.get()).removeModifier(UUID.fromString("e4fc11f4-1915-4a88-bc02-f9feb8e5f0b9"));
	}
}

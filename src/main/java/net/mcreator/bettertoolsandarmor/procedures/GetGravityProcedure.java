package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class GetGravityProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "" + new java.text.DecimalFormat("#.###").format(((LivingEntity) entity).getAttribute(ForgeMod.ENTITY_GRAVITY.get()).getValue());
	}
}

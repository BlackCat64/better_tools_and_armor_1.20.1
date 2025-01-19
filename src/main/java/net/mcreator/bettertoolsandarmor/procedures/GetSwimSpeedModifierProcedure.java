package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class GetSwimSpeedModifierProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		double modifier = 0;
		modifier = ((LivingEntity) entity).getAttribute(ForgeMod.SWIM_SPEED.get()).getValue() - ((LivingEntity) entity).getAttribute(ForgeMod.SWIM_SPEED.get()).getBaseValue();
		if (modifier > 0) {
			return "+" + new java.text.DecimalFormat("#.##").format(modifier);
		}
		return "" + new java.text.DecimalFormat("#.##").format(modifier);
	}
}

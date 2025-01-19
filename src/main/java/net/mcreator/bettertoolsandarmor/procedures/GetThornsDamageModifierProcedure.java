package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

public class GetThornsDamageModifierProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		double modifier = 0;
		modifier = ((LivingEntity) entity).getAttribute(BetterToolsModAttributes.THORNSDAMAGE.get()).getValue() - ((LivingEntity) entity).getAttribute(BetterToolsModAttributes.THORNSDAMAGE.get()).getBaseValue();
		if (modifier > 0) {
			return "+" + new java.text.DecimalFormat("##.#").format(modifier);
		}
		return "" + new java.text.DecimalFormat("##.#").format(modifier);
	}
}

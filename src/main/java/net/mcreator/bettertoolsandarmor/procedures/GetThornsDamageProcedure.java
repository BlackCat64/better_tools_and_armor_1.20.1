package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

public class GetThornsDamageProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "" + new java.text.DecimalFormat("##.#").format(((LivingEntity) entity).getAttribute(BetterToolsModAttributes.THORNSDAMAGE.get()).getValue());
	}
}

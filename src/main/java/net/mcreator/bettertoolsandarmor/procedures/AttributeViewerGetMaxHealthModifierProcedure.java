package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class AttributeViewerGetMaxHealthModifierProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		double modifier = 0;
		modifier = Math.floor(
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getValue() - ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getBaseValue());
		if (modifier > 0) {
			return "+" + new java.text.DecimalFormat("##").format(modifier);
		}
		return "" + new java.text.DecimalFormat("##").format(modifier);
	}
}

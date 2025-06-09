package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

public class EnergyTimeDisplayProcedure {
	public static String execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return "";
		double time_secs = 0;
		double cost_5s = 0;
		double energy = 0;
		energy = itemstack.getOrCreateTag().getDouble("energy");
		cost_5s = ((LivingEntity) entity).getAttribute(BetterToolsModAttributes.EFFECTENERGYCOST.get()).getValue();
		if (energy > 0 && cost_5s > 0) {
			time_secs = (energy / cost_5s) * 5;
			return new java.text.DecimalFormat("#").format(Math.floor(time_secs / 60)) + ":" + (time_secs % 60 < 10 ? "0" : "") + new java.text.DecimalFormat("#").format(time_secs % 60);
		}
		return "--:--";
	}
}

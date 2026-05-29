package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.component.DataComponents;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

public class EnergyTimeDisplayProcedure {
	public static String execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return "";
		double time_secs = 0;
		double cost_5s = 0;
		double energy = 0;
		double seconds = 0;
		energy = itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("energy");
		cost_5s = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).effect_energy_cost;
		if (energy > 0 && cost_5s > 0) {
			time_secs = Math.floor((energy / cost_5s) * 5);
			seconds = time_secs % 60;
			return new java.text.DecimalFormat("#").format(Math.floor(time_secs / 60)) + ":" + (seconds < 10 ? "0" : "") + new java.text.DecimalFormat("#").format(seconds);
		}
		return "--:--";
	}
}
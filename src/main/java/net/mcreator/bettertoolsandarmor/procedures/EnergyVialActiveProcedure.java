package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.component.DataComponents;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

public class EnergyVialActiveProcedure {
	public static double execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return 0;
		if (true) {
			if (EnergyVialActiveArmorPiecesProcedure.execute(entity, itemstack) > 0
					&& itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("energy") >= entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).effect_energy_cost
					|| (entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
				return 1;
			}
		}
		return 0;
	}
}
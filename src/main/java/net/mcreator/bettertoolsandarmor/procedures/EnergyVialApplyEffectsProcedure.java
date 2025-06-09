package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

public class EnergyVialApplyEffectsProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (itemstack.getOrCreateTag().getDouble("energy") > 0) {
			if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).effect_energy_cost > 0) {
				itemstack.getOrCreateTag().putDouble("energy",
						Math.max(itemstack.getOrCreateTag().getDouble("energy") - (entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).effect_energy_cost, 0));
				if (itemstack.getOrCreateTag().getBoolean("helmet_active")) {
					ApplyHelmetEffectProcedure.execute(entity);
				}
				if (itemstack.getOrCreateTag().getBoolean("chestplate_active")) {
					ApplyChestplateEffectProcedure.execute(entity);
				}
				if (itemstack.getOrCreateTag().getBoolean("leggings_active")) {
					ApplyLeggingsEffectProcedure.execute(entity);
				}
				if (itemstack.getOrCreateTag().getBoolean("boots_active")) {
					ApplyBootsEffectProcedure.execute(entity);
				}
			}
		} else {
			itemstack.getOrCreateTag().putDouble("energy", 0);
		}
	}
}

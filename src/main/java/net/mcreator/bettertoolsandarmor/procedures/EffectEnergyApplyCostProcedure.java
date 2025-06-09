package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

public class EffectEnergyApplyCostProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double timer = 0;
		{
			double _setval = CalculateEffectEnergyCostProcedure.execute(entity, itemstack);
			entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.effect_energy_cost = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (EnergyVialActiveProcedure.execute(itemstack) > 0) {
			if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).effect_energy_timer <= 0) {
				{
					double _setval = 100;
					entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.effect_energy_timer = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				EnergyVialApplyEffectsProcedure.execute(entity, itemstack);
			} else {
				{
					double _setval = (entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).effect_energy_timer - 1;
					entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.effect_energy_timer = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		} else {
			{
				double _setval = 0;
				entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.effect_energy_timer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}

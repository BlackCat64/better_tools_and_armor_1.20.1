package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

public class EffectEnergyApplyCostProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (EnergyVialActiveProcedure.execute(itemstack) > 0) {
			if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).effect_energy_timer <= 0) {
				{
					double _setval = 100;
					entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.effect_energy_timer = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (itemstack.getOrCreateTag().getDouble("energy") > 0) {
					itemstack.getOrCreateTag().putDouble("energy", Math.max(itemstack.getOrCreateTag().getDouble("energy") - ((LivingEntity) entity).getAttribute(BetterToolsModAttributes.EFFECTENERGYCOST.get()).getValue(), 0));
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
				} else {
					itemstack.getOrCreateTag().putDouble("energy", 0);
				}
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

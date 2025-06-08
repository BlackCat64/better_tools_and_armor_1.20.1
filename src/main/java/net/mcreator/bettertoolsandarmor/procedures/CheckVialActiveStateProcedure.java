package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class CheckVialActiveStateProcedure {
	public static boolean execute(LevelAccessor world, Entity entity, String armor) {
		if (entity == null || armor == null)
			return false;
		ItemStack vial = ItemStack.EMPTY;
		if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.ENERGY_VIAL.get(), lv).isPresent() : false) {
			if (entity instanceof LivingEntity lv) {
				CuriosApi.getCuriosHelper().findCurios(lv, BetterToolsModItems.ENERGY_VIAL.get()).forEach(item -> {
					ItemStack itemstackiterator = item.stack();
					{
						ItemStack _setval = itemstackiterator;
						entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.energy_vial_to_update = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				});
			}
		} else {
			{
				ItemStack _setval = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
				entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.energy_vial_to_update = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if (((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).energy_vial_to_update).getItem() == BetterToolsModItems.ENERGY_VIAL.get()) {
			return ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).energy_vial_to_update).getOrCreateTag().getBoolean((armor + "_active"));
		}
		return false;
	}
}

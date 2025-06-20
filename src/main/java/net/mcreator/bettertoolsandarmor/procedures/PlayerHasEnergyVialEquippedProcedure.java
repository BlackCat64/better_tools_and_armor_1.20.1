package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class PlayerHasEnergyVialEquippedProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity instanceof LivingEntity lv
				? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.ENERGY_VIAL.get(), lv).isPresent()
				: false || (entity instanceof LivingEntity lv
						? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.EMERALD_ENERGY_VIAL.get(), lv).isPresent()
						: false || entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.NETHERITE_ENERGY_VIAL.get(), lv).isPresent() : false);
	}
}

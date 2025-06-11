package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class EnergyVialActiveProcedure {
	public static double execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return 0;
		if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(itemstack.getItem(), lv).isPresent() : false) {
			if (EnergyVialActiveArmorPiecesProcedure.execute(entity, itemstack) > 0) {
				return 1;
			}
		}
		return 0;
	}
}

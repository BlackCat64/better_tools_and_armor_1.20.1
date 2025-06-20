package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class GetEquippedVialProcedure {
	public static ItemStack execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return ItemStack.EMPTY;
		ItemStack vial = ItemStack.EMPTY;
		ItemStack[] vial_arr = {ItemStack.EMPTY};
		if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.NETHERITE_ENERGY_VIAL.get(), lv).isPresent() : false) {
			if (entity instanceof LivingEntity lv) {
				CuriosApi.getCuriosHelper().findCurios(lv, BetterToolsModItems.NETHERITE_ENERGY_VIAL.get()).forEach(item -> {
					ItemStack itemstackiterator = item.stack();
					vial_arr[0] = itemstackiterator;
				});
			}
		} else if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.EMERALD_ENERGY_VIAL.get(), lv).isPresent() : false) {
			if (entity instanceof LivingEntity lv) {
				CuriosApi.getCuriosHelper().findCurios(lv, BetterToolsModItems.EMERALD_ENERGY_VIAL.get()).forEach(item -> {
					ItemStack itemstackiterator = item.stack();
					vial_arr[0] = itemstackiterator;
				});
			}
		} else if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.ENERGY_VIAL.get(), lv).isPresent() : false) {
			if (entity instanceof LivingEntity lv) {
				CuriosApi.getCuriosHelper().findCurios(lv, BetterToolsModItems.ENERGY_VIAL.get()).forEach(item -> {
					ItemStack itemstackiterator = item.stack();
					vial_arr[0] = itemstackiterator;
				});
			}
		}
		vial = vial_arr[0];
		return vial;
	}
}

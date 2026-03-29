package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class CheckVialActiveStateProcedure {
	public static boolean execute(Entity entity, String armor) {
		if (entity == null || armor == null)
			return false;
		ItemStack vial = ItemStack.EMPTY;
		if (PlayerHasEnergyVialEquippedProcedure.execute()) {
			vial = GetEquippedVialProcedure.execute();
		} else {
			vial = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
		}
		return vial.getOrCreateTag().getBoolean((armor + "_active"));
	}
}

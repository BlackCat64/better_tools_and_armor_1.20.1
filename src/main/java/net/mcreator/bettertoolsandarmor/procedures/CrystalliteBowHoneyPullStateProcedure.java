package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class CrystalliteBowHoneyPullStateProcedure {
	public static double execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return 0;
		if (false) {
			itemstack.shrink(1);
		}
		if ((entity instanceof LivingEntity _entUseItem0 ? _entUseItem0.getUseItem() : ItemStack.EMPTY) == itemstack) {
			return ((entity instanceof LivingEntity _entUseTicks2 ? _entUseTicks2.getTicksUsingItem() : 0) - 1) % 5;
		}
		return 0;
	}
}
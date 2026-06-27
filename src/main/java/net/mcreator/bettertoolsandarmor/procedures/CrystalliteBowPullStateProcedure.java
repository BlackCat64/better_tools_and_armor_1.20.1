package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

public class CrystalliteBowPullStateProcedure {
	public static double execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return 0;
		if (false) {
			itemstack.shrink(1);
		}
		if ((entity instanceof LivingEntity _entUseItem0 ? _entUseItem0.getUseItem() : ItemStack.EMPTY) == itemstack) {
			if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).crystallite_bow_pull_time <= 71980) {
				return 3;
			} else if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).crystallite_bow_pull_time <= 71984) {
				return 2;
			} else if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).crystallite_bow_pull_time > 0) {
				return 1;
			}
		}
		return 0;
	}
}
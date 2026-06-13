package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;

public class PlayerHasEnergyVialEquippedProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		return !(GetEquippedVialProcedure.execute(world, entity).getItem() == ItemStack.EMPTY.getItem());
	}
}
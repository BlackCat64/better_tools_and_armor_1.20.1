package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;

public class BootsActiveWhenGuiOpenedProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		ItemStack vial = ItemStack.EMPTY;
		return CheckVialActiveStateProcedure.execute(world, entity, "boots");
	}
}

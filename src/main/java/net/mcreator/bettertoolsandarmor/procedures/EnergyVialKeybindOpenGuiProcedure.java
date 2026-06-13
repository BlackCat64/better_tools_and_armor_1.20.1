package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

public class EnergyVialKeybindOpenGuiProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (PlayerHasEnergyVialEquippedProcedure.execute(world, entity)) {
			EnergyVialOpenGuiProcedure.execute(world, entity);
		}
	}
}
package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

public class NatureRingUnequippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			boolean _setval = false;
			entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.nature_ring_equipped = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		CharmCounterDecrementProcedure.execute(entity);
	}
}

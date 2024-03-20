package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

public class CharmCounterIncrementProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).charms_equipped < 4) {
			{
				double _setval = (entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).charms_equipped + 1;
				entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.charms_equipped = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			{
				double _setval = 4;
				entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.charms_equipped = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}

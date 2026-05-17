package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

public class StickToCeilingKeyUpProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
			_vars.stick_to_ceiling = false;
			_vars.syncPlayerVariables(entity);
		}
	}
}

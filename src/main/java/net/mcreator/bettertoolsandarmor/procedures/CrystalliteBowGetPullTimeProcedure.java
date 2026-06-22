package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

public class CrystalliteBowGetPullTimeProcedure {
	public static void execute(Entity entity, double time) {
		if (entity == null)
			return;
		{
			BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
			_vars.crystallite_bow_pull_time = time;
			_vars.markSyncDirty();
		}
	}
}
package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

public class CrystallitePickaxeSculkProcedureProcedure {
	public static void execute(LevelAccessor world) {
		double count = 0;
		if (world instanceof ServerLevel _level) {
			_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Migrated to OreBreakEffectsProcedure"), false);
		}
	}
}
package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

public class CrystalliteClusterGeneratedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (false) {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("Crystallite cluster at: " + "X = " + x + ", Y = " + y + ", Z = " + z)), false);
			}
		}
	}
}
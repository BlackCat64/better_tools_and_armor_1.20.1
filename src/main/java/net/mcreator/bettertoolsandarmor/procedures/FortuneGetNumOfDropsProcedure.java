package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.Component;

public class FortuneGetNumOfDropsProcedure {
	public static double execute(LevelAccessor world, double level) {
		double rand = 0;
		rand = Mth.nextInt(RandomSource.create(), 0, (int) (level + 1));
		if (rand <= 1) {
			if (!world.isClientSide() && world.getServer() != null)
				world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("" + 1)), false);
			return 1;
		}
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("" + rand)), false);
		return rand;
	}
}

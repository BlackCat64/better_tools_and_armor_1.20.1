package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

public class FortuneGetNumOfDropsProcedure {
	public static double execute(double level) {
		double rand = 0;
		rand = Mth.nextInt(RandomSource.create(), 0, (int) (level + 1));
		if (rand <= 1) {
			return 1;
		}
		return rand;
	}
}

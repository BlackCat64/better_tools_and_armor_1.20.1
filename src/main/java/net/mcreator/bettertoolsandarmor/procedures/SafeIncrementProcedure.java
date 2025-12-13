package net.mcreator.bettertoolsandarmor.procedures;

public class SafeIncrementProcedure {
	public static double execute(double value) {
		if (value < 2147483647) {
			return value + 1;
		}
		return 2147483647;
	}
}

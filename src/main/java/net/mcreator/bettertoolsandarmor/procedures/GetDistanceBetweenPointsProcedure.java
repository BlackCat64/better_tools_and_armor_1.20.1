package net.mcreator.bettertoolsandarmor.procedures;

public class GetDistanceBetweenPointsProcedure {
	public static double execute(double x, double y, double z, double x2, double y2, double z2) {
		return Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2) + Math.pow(z - z2, 2));
	}
}

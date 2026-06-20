package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.network.chat.Component;

import java.util.List;

public class GetTooltipLineContainingProcedure {
	public static double execute(String text, List<Component> tooltip) {
		if (text == null || tooltip == null)
			return 0;
		double line = 0;
		while (line < tooltip.size()) {
			if ((tooltip.get((int) line).getString()).contains(text)) {
				return line;
			}
			line = line + 1;
		}
		return -1;
	}
}
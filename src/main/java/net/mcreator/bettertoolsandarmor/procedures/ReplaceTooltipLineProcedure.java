package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.network.chat.Component;

import java.util.List;

public class ReplaceTooltipLineProcedure {
	public static void execute(double line, String text, List<Component> tooltip) {
		if (text == null || tooltip == null)
			return;
		if (line < 0 || line >= tooltip.size()) {
			tooltip.add(Component.literal(text));
		} else {
			tooltip.set((int) line, Component.literal(text));
		}
	}
}
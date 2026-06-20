package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import java.util.List;

public class ReplaceTooltipLineWithComponentProcedure {
	public static void execute(MutableComponent component, double line, List<Component> tooltip) {
		if (component == null || tooltip == null)
			return;
		if (false) {
			BetterToolsMod.LOGGER.info(component.getString());
		}
		if (line < 0 || line >= tooltip.size()) {
			tooltip.add(component);
		} else {
			tooltip.set((int) line, component);
		}
	}
}
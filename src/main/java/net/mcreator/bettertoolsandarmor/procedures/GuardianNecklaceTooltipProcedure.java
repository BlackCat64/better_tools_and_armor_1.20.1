package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class GuardianNecklaceTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getEntity(), event.getItemStack(), event.getToolTip());
	}

	public static void execute(Entity entity, ItemStack itemstack, List<Component> tooltip) {
		execute(null, entity, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, Entity entity, ItemStack itemstack, List<Component> tooltip) {
		if (entity == null || tooltip == null)
			return;
		double damage = 0;
		double radius = 0;
		if (itemstack.getItem() == BetterToolsModItems.GUARDIAN_NECKLACE.get()) {
			damage = 1;
			radius = 6;
			if (entity.isInWaterRainOrBubble()) {
				damage = damage * 2;
				radius = radius * 2;
			}
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal("\u00A77Every 3s:"));
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##.#").format(damage) + " Water Pulse Damage")));
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##.#").format(radius) + " Block Radius")));
			} else {
				tooltip.add(Component.literal("\u00A78Press Shift for details"));
			}
		}
	}
}

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
public class CrystalliteLeggingsPrismarineTooltipProcedure {
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
		boolean rain = false;
		boolean water = false;
		if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_PRISMARINE_LEGGINGS.get()) {
			if (entity.isInWater()) {
				water = true;
				rain = true;
				tooltip.add(Component.literal("\u00A79Zero Gravity"));
			} else if (entity.isInWaterRainOrBubble()) {
				rain = true;
				tooltip.add(Component.literal("\u00A79-50% Gravity"));
			}
			if (!water) {
				if (Screen.hasShiftDown()) {
					if (!rain) {
						tooltip.add(Component.literal("\u00A77When in rain:"));
						tooltip.add(Component.literal("\u00A79-50% Gravity"));
					}
					tooltip.add(Component.literal("\u00A77When in water:"));
					tooltip.add(Component.literal("\u00A79Zero Gravity"));
				} else {
					tooltip.add(Component.literal("\u00A78Press Shift for details"));
				}
			}
		}
	}
}

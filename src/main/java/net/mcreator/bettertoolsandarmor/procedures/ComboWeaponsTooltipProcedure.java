package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class ComboWeaponsTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getItemStack(), event.getToolTip());
	}

	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		execute(null, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:combo_weapons")))) {
			tooltip.add(Component.literal("\u00A77Damage Increase:"));
			tooltip.add(Component.literal("\u00A79+1 Damage per hit"));
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal("\u00A77Limit:"));
				tooltip.add(Component.literal("\u00A79+3 Attack Damage"));
			} else {
				tooltip.add(Component.literal("\u00A78Press Shift for more details"));
			}
		}
	}
}
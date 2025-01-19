package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class SwiftSwimPotionTooltipProcedure {
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
		String potion_tooltip = "";
		if (itemstack.getItem() == Items.POTION || itemstack.getItem() == Items.SPLASH_POTION || itemstack.getItem() == Items.LINGERING_POTION) {
			potion_tooltip = tooltip.get((tooltip.size() - 1)).getString();
			if (potion_tooltip.contains("effect.better_tools.swift_swim") || potion_tooltip.contains("Swift Swim")) {
				tooltip.add(Component.literal(""));
				tooltip.add(Component.literal("\u00A75When Applied:"));
				tooltip.add(Component.literal(("\u00A79+" + (potion_tooltip.contains("potion.potency.1") || potion_tooltip.contains("II") ? "100" : "50") + "% Swim Speed")));
			}
		}
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class CryingObsidianHelmetTooltipProcedure {
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
		if (itemstack.getItem() == BetterToolsModItems.CRYING_OBSIDIAN_HELMET.get()) {
			tooltip.add(Component.literal("\u00A79Protects from death"));
			tooltip.add(Component.literal("\u00A7cDurability Cost: All"));
			tooltip.add(Component.literal(((itemstack.getDamageValue() <= 192 ? "\u00A7a" : "\u00A7c") + "Durability Requirement: 50%")));
		} else if (itemstack.getItem() == BetterToolsModItems.DIAMOND_CRYING_OBSIDIAN_HELMET.get()) {
			tooltip.add(Component.literal("\u00A79Protects from death"));
			tooltip.add(Component.literal("\u00A7cDurability Cost: All but 10"));
			tooltip.add(Component.literal(((itemstack.getDamageValue() <= 297 ? "\u00A7a" : "\u00A7c") + "Durability Requirement: 40%")));
		}
	}
}
package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class GoldCarrotHatTooltipProcedure {
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
		if (itemstack.getItem() == BetterToolsModItems.GOLD_CARROT_HELMET.get()) {
			tooltip.add(Component.literal("\u00A77Effect Applied:"));
			tooltip.add(Component.literal("\u00A79Night Vision (0:15) every 5s"));
			tooltip.add(Component.literal("\u00A7cDurability Cost: 1"));
		} else if (itemstack.getItem() == BetterToolsModItems.IRON_GOLD_CARROT_HELMET.get()) {
			tooltip.add(Component.literal("\u00A77Effect Applied:"));
			tooltip.add(Component.literal("\u00A79Night Vision (0:18) every 8s"));
			tooltip.add(Component.literal("\u00A7cDurability Cost: 1"));
		} else if (itemstack.getItem() == BetterToolsModItems.DIAMOND_GOLD_CARROT_HELMET.get()) {
			tooltip.add(Component.literal("\u00A77Effect Applied:"));
			tooltip.add(Component.literal("\u00A79Night Vision (0:25) every 15s"));
			tooltip.add(Component.literal("\u00A7cDurability Cost: 2"));
		}
	}
}

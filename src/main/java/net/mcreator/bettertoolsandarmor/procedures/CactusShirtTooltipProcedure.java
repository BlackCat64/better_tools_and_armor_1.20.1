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
public class CactusShirtTooltipProcedure {
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
		if (itemstack.getItem() == BetterToolsModItems.CACTUS_CHESTPLATE.get()) {
			tooltip.add(Component.literal("\u00A79+2 Thorns Damage to melee attackers"));
		} else if (itemstack.getItem() == BetterToolsModItems.IRON_CACTUS_CHESTPLATE.get()) {
			tooltip.add(Component.literal("\u00A79+3 Thorns Damage to melee attackers"));
		} else if (itemstack.getItem() == BetterToolsModItems.DIAMOND_CACTUS_CHESTPLATE.get()) {
			tooltip.add(Component.literal("\u00A79+5 Thorns Damage to melee attackers"));
		}
	}
}

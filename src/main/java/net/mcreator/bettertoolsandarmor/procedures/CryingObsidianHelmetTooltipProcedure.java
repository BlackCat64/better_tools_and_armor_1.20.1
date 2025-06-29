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

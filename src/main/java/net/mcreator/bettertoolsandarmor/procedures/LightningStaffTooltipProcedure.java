package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModEnchantments;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class LightningStaffTooltipProcedure {
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
		double strikes = 0;
		double cooldown = 0;
		double radius = 0;
		if (itemstack.getItem() == BetterToolsModItems.ELECTRIC_STAFF.get()) {
			if (Screen.hasShiftDown()) {
				strikes = itemstack.getEnchantmentLevel(BetterToolsModEnchantments.ENSORCELLATION.get()) + 1;
				radius = 2.5 + itemstack.getEnchantmentLevel(BetterToolsModEnchantments.ENSORCELLATION.get()) * 0.5;
				cooldown = 10 - 1.5 * itemstack.getEnchantmentLevel(BetterToolsModEnchantments.SWIFT_CAST.get());
				tooltip.add(Component.literal("\u00A77Staff Effects:"));
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##").format(strikes) + " Lightning Strike" + (strikes > 1 ? "s" : ""))));
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##.#").format(radius) + " Block Radius")));
				tooltip.add(Component.literal(("\u00A7c " + new java.text.DecimalFormat("##").format(cooldown) + "s Cooldown on hit")));
			} else {
				tooltip.add(Component.literal("\u00A78Press Shift for details"));
			}
		}
	}
}

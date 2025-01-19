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
import net.mcreator.bettertoolsandarmor.init.BetterToolsModEnchantments;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class GuardianStaffTooltipProcedure {
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
		double cooldown = 0;
		if (itemstack.getItem() == BetterToolsModItems.GUARDIAN_STAFF.get()) {
			if (Screen.hasShiftDown()) {
				damage = 6 + itemstack.getEnchantmentLevel(BetterToolsModEnchantments.ENSORCELLATION.get());
				radius = 4 + 2 * itemstack.getEnchantmentLevel(BetterToolsModEnchantments.ENSORCELLATION.get());
				if (entity.isInWaterRainOrBubble()) {
					damage = damage * 1.5;
					radius = radius * 1.5;
				}
				cooldown = 10 - 1.5 * itemstack.getEnchantmentLevel(BetterToolsModEnchantments.SWIFT_CAST.get());
				tooltip.add(Component.literal("\u00A77Staff Effects:"));
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##.#").format(damage) + " Water Pulse Damage")));
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##").format(radius) + " Block Radius")));
				tooltip.add(Component.literal(("\u00A7c " + new java.text.DecimalFormat("##.#").format(cooldown) + "s Cooldown")));
			} else {
				tooltip.add(Component.literal("\u00A78Press Shift for details"));
			}
		}
	}
}

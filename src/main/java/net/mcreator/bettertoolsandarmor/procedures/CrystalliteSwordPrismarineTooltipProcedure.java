package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class CrystalliteSwordPrismarineTooltipProcedure {
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
		String damage_str = "";
		double damage = 0;
		double initial_lines = 0;
		double boost = 0;
		if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_SWORD_PRISMARINE.get()) {
			damage = 9;
			boost = 3;
		} else if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_AXE_PRISMARINE.get()) {
			damage = 10.5;
			boost = 2.5;
		} else if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_DAGGER_PRISMARINE.get()) {
			damage = 7;
			boost = 3;
		}
		if (damage > 0) {
			if (entity.isInWaterRainOrBubble()) {
				damage = damage + boost;
				if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, itemstack) != 0) {
					damage = damage + 0.5 + 0.5 * itemstack.getEnchantmentLevel(Enchantments.SHARPNESS);
				}
				damage_str = new java.text.DecimalFormat("##.#").format(damage);
				initial_lines = tooltip.size();
				if (((ItemTooltipEvent) event).getFlags().isAdvanced()) {
					initial_lines = initial_lines - 2;
				}
				tooltip.set((int) (initial_lines - 2), Component.literal("\u00A72 " + damage_str + " Attack Damage"));
			} else {
				if (Screen.hasShiftDown()) {
					tooltip.add(Component.literal("\u00A77When it is wet:"));
					tooltip.add(Component.literal(("\u00A79+" + new java.text.DecimalFormat("##.#").format(boost) + " Attack Damage")));
				} else {
					tooltip.add(Component.literal("\u00A78Press Shift for details"));
				}
			}
		}
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class BowKnockbackTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, Minecraft.getInstance().level, event.getItemStack(), event.getToolTip());
	}

	public static void execute(LevelAccessor world, ItemStack itemstack, List<Component> tooltip) {
		execute(null, world, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		double value = 0;
		if (itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.PUNCH)) != 0 || itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_BOW_IRON.get()) {
			value = itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.PUNCH));
			if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_BOW_IRON.get()) {
				value = value + 2;
			}
			tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##").format(value) + " Arrow Knockback")));
		}
	}
}
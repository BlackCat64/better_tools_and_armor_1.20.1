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
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class KnockbackTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, Minecraft.getInstance().level, event.getEntity(), event.getItemStack(), event.getToolTip());
	}

	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack, List<Component> tooltip) {
		execute(null, world, entity, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, ItemStack itemstack, List<Component> tooltip) {
		if (entity == null || tooltip == null)
			return;
		double value = 0;
		if (itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK)) != 0 || itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:high_knockback_weapons")))) {
			if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:iron_upgraded_crystallite_items")))) {
				value = 2 + itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK));
			} else if (itemstack.getItem() == BetterToolsModItems.BLUE_SLIME_STICK.get()) {
				value = 5;
			} else {
				value = itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK));
			}
			if (HasCuriosItemEquippedProcedure.execute(world, entity, new ItemStack(BetterToolsModItems.BOUNCY_BRACELET.get()))) {
				value = value + 2;
			}
			if (!itemstack.is(ItemTags.create(ResourceLocation.parse("minecraft:tools")))) {
				tooltip.add(Component.literal("\u00A77When in Main Hand:"));
			}
			tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##").format(value) + " Attack Knockback")));
		}
	}
}
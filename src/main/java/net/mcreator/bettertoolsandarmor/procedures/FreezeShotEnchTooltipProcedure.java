package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class FreezeShotEnchTooltipProcedure {
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
		double FreezeShotChance = 0;
		double freeze_time = 0;
		if (itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("better_tools:freeze_shot")))) != 0
				|| itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_BOW_SAPPHIRE.get()) {
			FreezeShotChance = itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("better_tools:freeze_shot")))) * 0.1;
			freeze_time = itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("better_tools:freeze_shot")))) * 66;
			if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:sapphire_upgraded_crystallite_items")))) {
				FreezeShotChance = FreezeShotChance + 0.2;
				freeze_time = freeze_time == 0 ? 70 : freeze_time * 1.5;
			}
			if (IsInColdBiomeProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ())) {
				FreezeShotChance = FreezeShotChance * 2;
			}
			if (FreezeShotChance > 0) {
				FreezeShotChance = FreezeShotChance + (entity instanceof LivingEntity _livingEntity13 && _livingEntity13.getAttributes().hasAttribute(Attributes.LUCK) ? _livingEntity13.getAttribute(Attributes.LUCK).getValue() : 0) * 0.05;
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##").format(Math.min(100, FreezeShotChance * 100)) + "% Freeze Chance")));
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##.#").format(freeze_time / 20) + "s Freeze Time")));
			}
		}
	}
}
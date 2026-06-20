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
import net.minecraft.ChatFormatting;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class CrystalliteSwordSculkTooltipProcedure {
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
		double dmg_boost = 0;
		double replaceLine = 0;
		double damage = 0;
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:dark_damage_boost_weapons")))) {
			if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_swords")))) {
				damage = 9.5;
				dmg_boost = 2.5;
			} else if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_axes")))) {
				damage = 11;
				dmg_boost = 2;
			} else if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_daggers")))) {
				damage = 7.5;
				dmg_boost = 2.5;
			}
			if (IsPlayerInTheDarkProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity)) {
				damage = damage + dmg_boost;
				if (itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) != 0) {
					damage = damage + 0.5 + 0.5 * itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS));
				}
				replaceLine = GetTooltipLineContainingProcedure.execute("Attack Damage", tooltip);
				ReplaceTooltipLineWithComponentProcedure.execute(
						Component.literal(" ").append(Component.literal((new java.text.DecimalFormat("##.##").format(damage))).withStyle(ChatFormatting.BOLD).withColor(0x00aaaa)).append(Component.literal(" Attack Damage")).withColor(0x00aa00),
						replaceLine, tooltip);
			} else {
				tooltip.add(Component.literal("\u00A77When in the dark:"));
				tooltip.add(Component.literal(("\u00A79+" + new java.text.DecimalFormat("##.##").format(dmg_boost) + " Attack Damage")));
			}
		}
	}
}
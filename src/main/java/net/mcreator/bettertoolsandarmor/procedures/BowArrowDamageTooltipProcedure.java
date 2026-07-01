package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class BowArrowDamageTooltipProcedure {
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
		double base_damage = 0;
		double damage = 0;
		if ((itemstack.getItem() == Items.BOW || itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_bows")))) && !itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:honey_upgraded_crystallite_items")))) {
			tooltip.add(Component.literal(" "));
			tooltip.add(Component.literal("\u00A77When shot:"));
			if (itemstack.getItem() == Items.BOW) {
				base_damage = 2;
			} else {
				if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:netherite_upgraded_crystallite_items")))) {
					base_damage = 4.5;
				} else if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:ruby_upgraded_crystallite_items")))) {
					base_damage = 1.5;
				} else if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:sculk_upgraded_crystallite_items")))) {
					base_damage = 3.5;
				} else {
					base_damage = 3;
				}
			}
			damage = itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.POWER)) != 0
					? base_damage + 0.5 + itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.POWER)) * 0.5
					: base_damage;
			tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##.##").format(damage) + " Arrow Damage")));
			if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:diamond_upgraded_crystallite_items")))) {
				tooltip.add(Component.literal("\u00A7725% chance for:"));
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##.##").format(damage + 2.5) + " Arrow Damage")));
			} else if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:prismarine_upgraded_crystallite_items")))) {
				tooltip.add(Component.literal("\u00A77When it is wet:"));
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##.##").format(damage + 1) + " Arrow Damage")));
			} else if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:nether_diamond_upgraded_crystallite_items")))) {
				tooltip.add(Component.literal((((entity.level().dimension()) == Level.NETHER ? "\u00A72 4" : "\u00A72 2.5") + " Explosion Power")));
			} else if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:ruby_upgraded_crystallite_items")))) {
				tooltip.add(Component.literal("\u00A72 0.5x Charge Time"));
			}
		}
	}
}
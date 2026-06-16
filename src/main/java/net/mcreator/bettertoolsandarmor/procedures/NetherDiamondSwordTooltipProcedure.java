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
public class NetherDiamondSwordTooltipProcedure {
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
		String damage_str = "";
		double fire_chance = 0;
		double dmg_boost = 0;
		double damage = 0;
		double initial_lines = 0;
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:flaming_tools"))) && !(itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT)) != 0)) {
			if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:nether_diamond_upgraded_crystallite_items")))) {
				fire_chance = 5;
			} else {
				fire_chance = 3;
			}
			if ((entity.level().dimension()) == Level.NETHER) {
				fire_chance = fire_chance * 2;
			}
			tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("#").format(fire_chance) + "s Burn Time")));
			if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:nether_diamond_upgraded_crystallite_items")))) {
				if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_swords")))) {
					damage = 9;
					dmg_boost = 3;
				} else if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_axes")))) {
					damage = 10.5;
					dmg_boost = 2.5;
				} else if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:daggers")))) {
					damage = 7;
					dmg_boost = 3;
				}
				if ((entity.level().dimension()) == Level.NETHER) {
					damage = damage + dmg_boost;
					if (itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) != 0) {
						damage = damage + 0.5 + 0.5 * itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS));
					}
					damage_str = new java.text.DecimalFormat("##.#").format(damage);
					initial_lines = tooltip.size();
					if (((ItemTooltipEvent) event).getFlags().isAdvanced()) {
						initial_lines = initial_lines - 2;
					}
					tooltip.set((int) (initial_lines - 2), Component.literal("\u00A72 " + damage_str + " Attack Damage"));
				} else {
					tooltip.add(Component.literal("\u00A77When in The Nether:"));
					tooltip.add(Component.literal(("\u00A79+" + new java.text.DecimalFormat("##.#").format(dmg_boost) + " Attack Damage")));
				}
			}
		}
	}
}
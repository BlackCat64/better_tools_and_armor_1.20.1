package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class NetherDiamondSwordTooltipProcedure {
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
		double fire_chance = 0;
		double dmg_boost = 0;
		double damage = 0;
		double initial_lines = 0;
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:flaming_tools"))) && !(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_ASPECT, itemstack) != 0)) {
			if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:nether_diamond_upgraded_crystallite_items")))) {
				fire_chance = 5;
			} else {
				fire_chance = 3;
			}
			if ((entity.level().dimension()) == Level.NETHER) {
				fire_chance = fire_chance * 2;
			}
			tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("#").format(fire_chance) + "s Burn Time")));
			if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:nether_diamond_upgraded_crystallite_items")))) {
				if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_swords")))) {
					damage = 9;
					dmg_boost = 3;
				} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_axes")))) {
					damage = 10.5;
					dmg_boost = 2.5;
				} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:daggers")))) {
					damage = 7;
					dmg_boost = 3;
				}
				if ((entity.level().dimension()) == Level.NETHER) {
					damage = damage + dmg_boost;
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
					tooltip.add(Component.literal("\u00A77When in The Nether:"));
					tooltip.add(Component.literal(("\u00A79+" + new java.text.DecimalFormat("##.#").format(dmg_boost) + " Attack Damage")));
				}
			}
		}
	}
}

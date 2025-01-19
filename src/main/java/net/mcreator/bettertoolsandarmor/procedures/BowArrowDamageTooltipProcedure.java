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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class BowArrowDamageTooltipProcedure {
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
		double base_damage = 0;
		if ((itemstack.getItem() == Items.BOW || itemstack.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_bows")))) && !itemstack.is(ItemTags.create(new ResourceLocation("better_tools:honey_upgraded_crystallite_items")))) {
			tooltip.add(Component.literal(" "));
			tooltip.add(Component.literal("\u00A77When shot:"));
			if (itemstack.getItem() == Items.BOW) {
				base_damage = 2;
			} else {
				if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:netherite_upgraded_crystallite_items")))) {
					base_damage = 5;
				} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:ruby_upgraded_crystallite_items")))) {
					base_damage = 1.5;
				} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sculk_upgraded_crystallite_items")))) {
					base_damage = 4;
				} else {
					base_damage = 3.5;
				}
			}
			tooltip.add(Component.literal(
					("\u00A72 " + ("" + (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, itemstack) != 0 ? base_damage + 0.5 + itemstack.getEnchantmentLevel(Enchantments.POWER_ARROWS) * 0.5 : base_damage)).replace(".0", "")
							+ " Arrow Damage")));
			if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:diamond_upgraded_crystallite_items"))) || itemstack.is(ItemTags.create(new ResourceLocation("better_tools:prismarine_upgraded_crystallite_items")))) {
				tooltip.add(Component.literal((itemstack.is(ItemTags.create(new ResourceLocation("better_tools:diamond_upgraded_crystallite_items"))) ? "\u00A7725% chance for:" : "\u00A77When it is wet:")));
				tooltip.add(Component.literal(("\u00A72 "
						+ ("" + ((EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, itemstack) != 0 ? base_damage + 0.5 + itemstack.getEnchantmentLevel(Enchantments.POWER_ARROWS) * 0.5 : base_damage) + 2.5)).replace(".0", "")
						+ " Arrow Damage")));
			}
			if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:nether_diamond_upgraded_crystallite_items")))) {
				tooltip.add(Component.literal((((entity.level().dimension()) == Level.NETHER ? "\u00A72 4" : "\u00A72 2.5") + " Explosion Power")));
			}
		}
	}
}

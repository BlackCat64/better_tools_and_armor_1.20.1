package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class EffectArmorTooltipsProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getItemStack(), event.getToolTip());
	}

	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		execute(null, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:effect_armor")))) {
			tooltip.add(Component.literal("\u00A77Effect Applied:"));
		}
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:golden_carrot_hats")))) {
			tooltip.add(Component.literal("\u00A79Night Vision"));
		} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sugar_boots")))) {
			tooltip.add(Component.literal(("\u00A79Speed " + (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:diamond_tier_effect_armor"))) ? "III" : "II"))));
		} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:rabbit_boots")))) {
			tooltip.add(Component.literal(("\u00A79Jump Boost " + (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:diamond_tier_effect_armor"))) ? "III" : "II"))));
		} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:phantom_boots")))) {
			tooltip.add(Component.literal("\u00A79Slow Falling"));
		} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:magma_chestplates")))) {
			tooltip.add(Component.literal("\u00A79Strength"));
		} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:ruby_leggings")))) {
			tooltip.add(Component.literal(("\u00A79Haste " + (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:diamond_tier_effect_armor"))) ? "II" : ""))));
		} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:crystal_leggings")))) {
			tooltip.add(Component.literal(("\u00A79Swift Swim " + (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:diamond_tier_effect_armor"))) ? "III" : "II"))));
		} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:gilded_blackstone_leggings")))) {
			tooltip.add(Component.literal("\u00A79Fire Resistance"));
		} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:hearty_shirts")))) {
			tooltip.add(Component.literal("\u00A79Absorption"));
		}
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:base_tier_effect_armor")))) {
			tooltip.add(Component.literal("\u00A77Energy Cost: \u00A7c150 \u00A76/ 5s"));
		} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:iron_tier_effect_armor")))) {
			tooltip.add(Component.literal("\u00A77Energy Cost: \u00A7c120 \u00A76/ 5s"));
		} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:diamond_tier_effect_armor")))) {
			tooltip.add(Component.literal("\u00A77Energy Cost: \u00A7c90 \u00A76/ 5s"));
		}
	}
}

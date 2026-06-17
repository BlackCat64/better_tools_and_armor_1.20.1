package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class SapphireArmorTooltipProcedure {
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
		double percent = 0;
		double seconds = 0;
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:freezing_armor")))) {
			if (IsPlayerWearingItemProcedure.execute(entity, itemstack)) {
				percent = (entity instanceof LivingEntity _livingEntity2 && _livingEntity2.getAttributes().hasAttribute(BetterToolsModAttributes.FREEZE_THORNS_CHANCE)
						? _livingEntity2.getAttribute(BetterToolsModAttributes.FREEZE_THORNS_CHANCE).getValue()
						: 0) * 100;
				seconds = (entity instanceof LivingEntity _livingEntity3 && _livingEntity3.getAttributes().hasAttribute(BetterToolsModAttributes.FREEZE_THORNS_TIME)
						? _livingEntity3.getAttribute(BetterToolsModAttributes.FREEZE_THORNS_TIME).getValue()
						: 0) / 20;
			} else {
				if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:sapphire_upgraded_crystallite_items")))) {
					percent = 8;
					seconds = 10;
				} else {
					percent = 4;
					seconds = 5;
				}
				if (IsInColdBiomeProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ())) {
					percent = percent * (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:sapphire_upgraded_crystallite_items"))) ? 1.5 : 2);
					seconds = seconds * (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:sapphire_upgraded_crystallite_items"))) ? 1.5 : 2);
				}
			}
			tooltip.add(Component.literal(((!IsPlayerWearingItemProcedure.execute(entity, itemstack) ? "\u00A79+" : "\u00A72 ") + "" + new java.text.DecimalFormat("##.#").format(percent) + "% Freeze Chance")));
			tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##.#").format(seconds) + "s Freeze Time")));
		}
	}
}
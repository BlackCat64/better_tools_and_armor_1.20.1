package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
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
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:freezing_armor")))) {
			if (IsPlayerWearingItemProcedure.execute(entity, itemstack)) {
				percent = ((LivingEntity) entity).getAttribute(BetterToolsModAttributes.FREEZETHORNSCHANCE.get()).getValue() * 100;
				seconds = ((LivingEntity) entity).getAttribute(BetterToolsModAttributes.FREEZETHORNSTIME.get()).getValue() / 20;
			} else {
				if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sapphire_upgraded_crystallite_items")))) {
					percent = 8;
					seconds = 10;
				} else {
					percent = 4;
					seconds = 5;
				}
				if (IsInColdBiomeProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ())) {
					percent = percent * (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sapphire_upgraded_crystallite_items"))) ? 1.5 : 2);
					seconds = seconds * (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:sapphire_upgraded_crystallite_items"))) ? 1.5 : 2);
				}
			}
			tooltip.add(Component.literal(((!IsPlayerWearingItemProcedure.execute(entity, itemstack) ? "\u00A79+" : "\u00A72 ") + "" + new java.text.DecimalFormat("##.#").format(percent) + "% Freeze Chance")));
			tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##.#").format(seconds) + "s Freeze Time")));
		}
	}
}

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
public class GlassArmorTooltipProcedure {
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
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:glass_armor")))) {
			tooltip.add(Component.literal("\u00A77Effects Applied when full set worn:"));
			tooltip.add(Component.literal("\u00A7920% Detection Range for all nearby mobs"));
			tooltip.add(Component.literal("\u00A79Invisibility"));
			if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:base_glass_armor")))) {
				tooltip.add(Component.literal("\u00A77Energy Cost: \u00A7c160 \u00A76/ 5s"));
			} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:iron_glass_armor")))) {
				tooltip.add(Component.literal("\u00A77Energy Cost: \u00A7c120 \u00A76/ 5s"));
			} else if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:diamond_glass_armor")))) {
				tooltip.add(Component.literal("\u00A77Energy Cost: \u00A7c100 \u00A76/ 5s"));
			}
		}
	}
}

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

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class HeartyShirtTooltipProcedure {
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
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:hearty_shirts")))) {
			tooltip.add(Component.literal("\u00A77Effect Applied:"));
			tooltip.add(Component.literal("\u00A79Absorption"));
			if (itemstack.getItem() == BetterToolsModItems.HEARTY_CHESTPLATE.get()) {
				tooltip.add(Component.literal("\u00A79Up to 4HP"));
				tooltip.add(Component.literal("\u00A77Energy Cost: \u00A7c150 \u00A76/ 1HP"));
			} else if (itemstack.getItem() == BetterToolsModItems.IRON_HEARTY_CHESTPLATE.get()) {
				tooltip.add(Component.literal("\u00A79Up to 4HP"));
				tooltip.add(Component.literal("\u00A77Energy Cost: \u00A7c120 \u00A76/ 1HP"));
			} else if (itemstack.getItem() == BetterToolsModItems.DIAMOND_HEARTY_CHESTPLATE.get()) {
				tooltip.add(Component.literal("\u00A79Up to 8HP"));
				tooltip.add(Component.literal("\u00A77Energy Cost: \u00A7c90 \u00A76/ 1HP"));
			}
		}
	}
}

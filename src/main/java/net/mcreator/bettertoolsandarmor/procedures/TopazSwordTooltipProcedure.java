package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class TopazSwordTooltipProcedure {
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
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:lightning_tools")))) {
			tooltip.add(Component.literal(("\u00A72 " + (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:topaz_upgraded_crystallite_items"))) ? "50" : "25") + "% Chain Chance")));
			tooltip.add(Component.literal("\u00A72 3 Block Radius"));
			tooltip.add(Component.literal("\u00A77Chain Attack Damage:"));
			tooltip.add(Component.literal("\u00A7950% of Weapon Damage"));
		}
	}
}
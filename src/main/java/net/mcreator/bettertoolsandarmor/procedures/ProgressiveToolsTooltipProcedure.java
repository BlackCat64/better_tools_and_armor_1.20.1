package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.registries.ForgeRegistries;
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
public class ProgressiveToolsTooltipProcedure {
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
		double threshold_1 = 0;
		double threshold_2 = 0;
		if (itemstack.is(ItemTags.create(new ResourceLocation("better_tools:progressive_tools")))) {
			if (itemstack.is(ItemTags.create(new ResourceLocation("minecraft:axes")))) {
				threshold_1 = 500;
				threshold_2 = 2000;
			} else {
				threshold_1 = 1600;
				threshold_2 = 6400;
			}
			tooltip.add(Component.literal(("\u00A77Blocks Mined: \u00A76" + new java.text.DecimalFormat("##.##").format(itemstack.getOrCreateTag().getDouble("blocks_mined")))));
			if (!(ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()).endsWith("_upgrade_2")) {
				tooltip.add(Component.literal(("\u00A77Next Upgrade at: \u00A76" + new java.text.DecimalFormat("##.##").format((ForgeRegistries.ITEMS.getKey(itemstack.getItem()).toString()).endsWith("_upgrade_1") ? threshold_2 : threshold_1))));
			}
		}
	}
}

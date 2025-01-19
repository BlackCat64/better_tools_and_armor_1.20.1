package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
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
		if (itemstack.getItem() == BetterToolsModItems.TOPAZ_SWORD.get() || itemstack.getItem() == BetterToolsModItems.TOPAZ_DAGGER.get() || itemstack.getItem() == BetterToolsModItems.TOPAZ_AXE.get()) {
			tooltip.add(Component.literal("\u00A72 25% Chain Chance"));
			tooltip.add(Component.literal("\u00A72 3 Block Radius"));
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal("\u00A77Chain Attack Damage:"));
				tooltip.add(Component.literal("\u00A7950% of Weapon Damage"));
				tooltip.add(Component.literal("\u00A77When in a thunderstorm:"));
				tooltip.add(Component.literal("\u00A79+12.5% Chain Chance"));
				tooltip.add(Component.literal("\u00A79+3 Block Radius"));
			} else {
				tooltip.add(Component.literal("\u00A78Press Shift for details"));
			}
		}
	}
}

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
public class CrystalliteArmorIronTooltipProcedure {
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
		if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_IRON_HELMET.get() || itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_IRON_CHESTPLATE.get()
				|| itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_IRON_LEGGINGS.get() || itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_IRON_BOOTS.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal("\u00A77When full set worn:"));
				tooltip.add(Component.literal("\u00A79Inflict melee attackers with Slowness II for 3s"));
			} else {
				tooltip.add(Component.literal("\u00A78Press Shift for details"));
			}
		}
	}
}

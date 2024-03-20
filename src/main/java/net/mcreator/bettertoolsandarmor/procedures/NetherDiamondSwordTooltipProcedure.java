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
public class NetherDiamondSwordTooltipProcedure {
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
		if (itemstack.getItem() == BetterToolsModItems.NETHER_DIAMOND_SWORD.get() || itemstack.getItem() == BetterToolsModItems.NETHER_DIAMOND_DAGGER.get() || itemstack.getItem() == BetterToolsModItems.NETHER_DIAMOND_AXE.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal("\u00A77Weapon Effects:"));
				tooltip.add(Component.literal("\u00A7910% \u00A74chance for \u00A795s \u00A74of fire"));
				tooltip.add(Component.literal("\u00A77When in the Nether:"));
				tooltip.add(Component.literal("\u00A7920% \u00A74chance for \u00A7910s \u00A74of fire"));
			} else {
				tooltip.add(Component.literal("\u00A78Press Shift for details"));
			}
		}
	}
}

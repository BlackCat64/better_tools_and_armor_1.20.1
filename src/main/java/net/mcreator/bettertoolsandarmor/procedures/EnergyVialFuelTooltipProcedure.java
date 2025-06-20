package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.world.inventory.EnergyVialMenuMenu;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class EnergyVialFuelTooltipProcedure {
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
		if (entity instanceof Player _plr0 && _plr0.containerMenu instanceof EnergyVialMenuMenu) {
			if (itemstack.getItem() == Items.BLAZE_POWDER) {
				tooltip.add(Component.literal("\u00A77Effect Energy: \u00A761000"));
			} else if (itemstack.getItem() == Items.NETHER_WART) {
				tooltip.add(Component.literal("\u00A77Effect Energy: \u00A76500"));
			}
		}
	}
}

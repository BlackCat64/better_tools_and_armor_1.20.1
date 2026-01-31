package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModEnchantments;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class IceStaffTooltipProcedure {
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
		double cooldown = 0;
		double radius = 0;
		if (itemstack.getItem() == BetterToolsModItems.ICE_STAFF.get()) {
			radius = 1 + itemstack.getEnchantmentLevel(BetterToolsModEnchantments.ENSORCELLATION.get());
			cooldown = 10 - 1.5 * itemstack.getEnchantmentLevel(BetterToolsModEnchantments.SWIFT_CAST.get());
			tooltip.add(Component.literal("\u00A77Staff Effects:"));
			tooltip.add(Component.literal((IsInColdBiomeProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ()) ? "\u00A72 15s Freeze Time" : "\u00A72 10s Freeze Time")));
			if (EnchantmentHelper.getItemEnchantmentLevel(BetterToolsModEnchantments.ENSORCELLATION.get(), itemstack) != 0) {
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##.#").format((IsInColdBiomeProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ()) ? radius + 2 : radius) / 2) + " Block Radius")));
			}
			tooltip.add(Component.literal(("\u00A7c " + (new java.text.DecimalFormat("##.#").format(cooldown)).replace(".0", "") + "s Cooldown on hit")));
		}
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class IceStaffTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, Minecraft.getInstance().level, event.getEntity(), event.getItemStack(), event.getToolTip());
	}

	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack, List<Component> tooltip) {
		execute(null, world, entity, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, ItemStack itemstack, List<Component> tooltip) {
		if (entity == null || tooltip == null)
			return;
		double cooldown = 0;
		double radius = 0;
		if (itemstack.getItem() == BetterToolsModItems.ICE_STAFF.get()) {
			radius = 1 + itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("better_tools:ensorcellation"))));
			cooldown = 10 - 1.5 * itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("better_tools:swift_cast"))));
			tooltip.add(Component.literal("\u00A77Staff Effects:"));
			tooltip.add(Component.literal((IsInColdBiomeProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ()) ? "\u00A72 15s Freeze Time" : "\u00A72 10s Freeze Time")));
			if (itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("better_tools:ensorcellation")))) != 0) {
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##.#").format((IsInColdBiomeProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ()) ? radius + 2 : radius) / 2) + " Block Radius")));
			}
			tooltip.add(Component.literal(("\u00A7c " + (new java.text.DecimalFormat("##.#").format(cooldown)).replace(".0", "") + "s Cooldown on hit")));
		}
	}
}
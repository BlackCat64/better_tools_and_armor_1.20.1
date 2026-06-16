package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class FireAspectTooltipProcedure {
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
		double time = 0;
		if (itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT)) != 0) {
			time = itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT)) * 4;
			if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:flaming_tools")))) {
				if ((entity.level().dimension()) == Level.NETHER) {
					time = Math.max(10, time);
				} else {
					time = Math.max(5, time);
				}
			}
			tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##").format(time) + "s Burn Time")));
		}
	}
}
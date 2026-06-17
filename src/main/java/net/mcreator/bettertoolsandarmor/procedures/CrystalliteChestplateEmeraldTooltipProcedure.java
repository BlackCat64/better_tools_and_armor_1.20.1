package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class CrystalliteChestplateEmeraldTooltipProcedure {
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
		if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_EMERALD_CHESTPLATE.get()) {
			time = 8;
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) / 2) {
				time = time / 2;
			}
			if (IsPlayerInSunlightProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity)) {
				time = time / 2;
			}
			if (HasCuriosItemEquippedProcedure.execute(world, entity, new ItemStack(BetterToolsModItems.NATURE_RING.get()))) {
				time = time / 2;
			}
			tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##.##").format(time) + "s Regeneration Speed")));
		}
	}
}
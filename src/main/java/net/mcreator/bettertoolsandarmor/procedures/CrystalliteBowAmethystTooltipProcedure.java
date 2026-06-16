package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class CrystalliteBowAmethystTooltipProcedure {
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
		double radius = 0;
		if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_BOW_AMETHYST.get()) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_BOW_AMETHYST.get()
					|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_BOW_AMETHYST.get()) {
				radius = radius + 1.5;
				if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_AMETHYST_HELMET.get()) {
					radius = radius + 1.5;
				}
			}
		} else if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_AMETHYST_HELMET.get()) {
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_AMETHYST_HELMET.get()) {
				radius = radius + 1.5;
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_BOW_AMETHYST.get()
						|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_BOW_AMETHYST.get()) {
					radius = radius + 1.5;
				}
			}
		} else {
			radius = -1;
		}
		if (radius >= 0) {
			tooltip.add(Component.literal("\u00A77When fully charged Arrows are fired: "));
			if (radius > 0) {
				tooltip.add(Component.literal(("\u00A72 " + radius + " Blocks Homing Radius")));
			} else {
				tooltip.add(Component.literal("\u00A79+1.5 Blocks Homing Radius"));
			}
		}
	}
}
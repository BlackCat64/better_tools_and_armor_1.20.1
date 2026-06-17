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
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class CrystalliteNetheriteArmorTooltipProcedure {
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
		if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHERITE_HELMET.get() || itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHERITE_CHESTPLATE.get()
				|| itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHERITE_LEGGINGS.get() || itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHERITE_BOOTS.get()) {
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == itemstack.getItem()
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == itemstack.getItem()
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == itemstack.getItem()
					|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == itemstack.getItem()) {
				tooltip.add(Component.literal(("\u00A72 " + (new java.text.DecimalFormat("##").format(
						entity instanceof LivingEntity _livingEntity20 && _livingEntity20.getAttributes().hasAttribute(BetterToolsModAttributes.THORNS_DAMAGE) ? _livingEntity20.getAttribute(BetterToolsModAttributes.THORNS_DAMAGE).getValue() : 0))
						+ " Thorns Damage")));
				if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHERITE_HELMET.get()
						&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHERITE_CHESTPLATE.get()
						&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHERITE_LEGGINGS.get()
						&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHERITE_BOOTS.get()) {
					tooltip.add(Component.literal("\u00A72 1 Block Radius"));
				}
			} else {
				if ((entity instanceof LivingEntity _livingEntity31 && _livingEntity31.getAttributes().hasAttribute(BetterToolsModAttributes.THORNS_DAMAGE) ? _livingEntity31.getAttribute(BetterToolsModAttributes.THORNS_DAMAGE).getValue() : 0) == 0) {
					tooltip.add(Component.literal("\u00A79+2 Thorns Damage"));
				} else {
					tooltip.add(Component.literal("\u00A79+1 Thorns Damage"));
				}
			}
		}
	}
}
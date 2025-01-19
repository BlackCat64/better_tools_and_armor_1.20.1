package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class NetherDiamondArmorSetAttributesProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		double armor_pieces = 0;
		double chance = 0;
		double time = 0;
		if (entity instanceof LivingEntity && ((LivingEntity) entity).getAttribute(BetterToolsModAttributes.FIRETHORNSCHANCE.get()) != null) {
			time = 5;
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.NETHER_DIAMOND_BOOTS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.05;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHER_DIAMOND_BOOTS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.1;
				time = 10;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.NETHER_DIAMOND_LEGGINGS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.05;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHER_DIAMOND_LEGGINGS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.1;
				time = 10;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.NETHER_DIAMOND_CHESTPLATE.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.05;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHER_DIAMOND_CHESTPLATE.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.1;
				time = 10;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.NETHER_DIAMOND_HELMET.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.05;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHER_DIAMOND_HELMET.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.1;
				time = 10;
			}
			if (armor_pieces == 4) {
				if (time == 10) {
					chance = chance + 0.1;
				} else {
					chance = chance + 0.05;
				}
			}
			if ((entity.level().dimension()) == Level.NETHER) {
				if (time == 10) {
					chance = chance * 1.5;
					time = time * 1.5;
				} else {
					chance = chance * 2;
					time = time * 2;
				}
			}
			if (chance > 0) {
				if (entity instanceof LivingEntity && ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK) != null) {
					chance = chance + ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).getValue() * 0.05;
				}
			}
			((LivingEntity) entity).getAttribute(BetterToolsModAttributes.FIRETHORNSCHANCE.get()).removeModifier((new AttributeModifier(UUID.fromString("2aa8c393-189a-4e10-941b-77b72ab562a7"), "", 0, AttributeModifier.Operation.ADDITION)));
			((LivingEntity) entity).getAttribute(BetterToolsModAttributes.FIRETHORNSTIME.get()).removeModifier((new AttributeModifier(UUID.fromString("99d73964-684f-4d09-8f01-74f9af202183"), "", 0, AttributeModifier.Operation.ADDITION)));
			if (armor_pieces > 0) {
				((LivingEntity) entity).getAttribute(BetterToolsModAttributes.FIRETHORNSCHANCE.get())
						.addTransientModifier((new AttributeModifier(UUID.fromString("2aa8c393-189a-4e10-941b-77b72ab562a7"), "nether_diamond_armor", chance, AttributeModifier.Operation.ADDITION)));
				((LivingEntity) entity).getAttribute(BetterToolsModAttributes.FIRETHORNSTIME.get())
						.addTransientModifier((new AttributeModifier(UUID.fromString("99d73964-684f-4d09-8f01-74f9af202183"), "nether_diamond_armor", time, AttributeModifier.Operation.ADDITION)));
			}
		}
	}
}

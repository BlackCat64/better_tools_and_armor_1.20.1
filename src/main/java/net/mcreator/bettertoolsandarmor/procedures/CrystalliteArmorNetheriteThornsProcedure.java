package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

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
public class CrystalliteArmorNetheriteThornsProcedure {
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
		double damage = 0;
		double armor_pieces = 0;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHERITE_HELMET.get()) {
			armor_pieces = armor_pieces + 1;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHERITE_CHESTPLATE.get()) {
			armor_pieces = armor_pieces + 1;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHERITE_LEGGINGS.get()) {
			armor_pieces = armor_pieces + 1;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_NETHERITE_BOOTS.get()) {
			armor_pieces = armor_pieces + 1;
		}
		if (armor_pieces > 0) {
			damage = armor_pieces + 1;
		}
		if (armor_pieces == 4) {
			damage = damage + 1;
		}
		((LivingEntity) entity).getAttribute(BetterToolsModAttributes.THORNSDAMAGE.get()).removeModifier((new AttributeModifier(UUID.fromString("708dd714-19b0-4dd0-9509-30124a4a1845"), "", 0, AttributeModifier.Operation.ADDITION)));
		if (damage > 0) {
			((LivingEntity) entity).getAttribute(BetterToolsModAttributes.THORNSDAMAGE.get())
					.addTransientModifier((new AttributeModifier(UUID.fromString("708dd714-19b0-4dd0-9509-30124a4a1845"), "crystallite_armor_netherite", damage, AttributeModifier.Operation.ADDITION)));
		}
	}
}

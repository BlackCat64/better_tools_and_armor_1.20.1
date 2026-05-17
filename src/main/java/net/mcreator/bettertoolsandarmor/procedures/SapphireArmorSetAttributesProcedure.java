package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class SapphireArmorSetAttributesProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double armor_pieces = 0;
		double time = 0;
		double chance = 0;
		boolean crystallite = false;
		if (entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(BetterToolsModAttributes.FREEZE_THORNS_CHANCE.get())) {
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.SAPPHIRE_BOOTS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.04;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SAPPHIRE_BOOTS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.08;
				crystallite = true;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.SAPPHIRE_LEGGINGS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.04;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SAPPHIRE_LEGGINGS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.08;
				crystallite = true;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.SAPPHIRE_CHESTPLATE.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.04;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SAPPHIRE_CHESTPLATE.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.08;
				crystallite = true;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.SAPPHIRE_HELMET.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.04;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SAPPHIRE_HELMET.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.08;
				crystallite = true;
			}
			time = crystallite ? 200 : 100;
			if (armor_pieces == 4) {
				chance = chance + (crystallite ? 0.08 : 0.04);
			}
			if (IsInColdBiomeProcedure.execute(world, x, y, z)) {
				chance = chance * (crystallite ? 1.5 : 2);
				time = time * (crystallite ? 1.5 : 2);
			}
			if (chance > 0) {
				chance = chance + (entity instanceof LivingEntity _livingEntity17 && _livingEntity17.getAttributes().hasAttribute(Attributes.LUCK) ? _livingEntity17.getAttribute(Attributes.LUCK).getValue() : 0) * 0.05;
				if (true) {
					chance = chance + 0.1;
				}
			}
		}
	}
}

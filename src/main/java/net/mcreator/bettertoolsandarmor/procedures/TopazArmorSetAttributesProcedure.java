package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class TopazArmorSetAttributesProcedure {
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
		double chance = 0;
		boolean crystallite_worn = false;
		if (false) {
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.TOPAZ_BOOTS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.04;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_TOPAZ_BOOTS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.08;
				crystallite_worn = true;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.TOPAZ_LEGGINGS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.04;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_TOPAZ_LEGGINGS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.08;
				crystallite_worn = true;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.TOPAZ_CHESTPLATE.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.04;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_TOPAZ_CHESTPLATE.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.08;
				crystallite_worn = true;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.TOPAZ_HELMET.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.04;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_TOPAZ_HELMET.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.08;
				crystallite_worn = true;
			}
			if (armor_pieces == 4) {
				chance = chance + (crystallite_worn ? 0.08 : 0.04);
			}
			if (IsInThunderstormProcedure.execute(world, x, y, z, entity)) {
				chance = chance * (crystallite_worn ? 1.5 : 2);
			}
			if (chance > 0) {
				if (false) {
					chance = chance + 1 * 0.05;
				}
			}
		}
	}
}

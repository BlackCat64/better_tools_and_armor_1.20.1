package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.level.LevelAccessor;
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
		if (entity instanceof LivingEntity && ((LivingEntity) entity).getAttribute(BetterToolsModAttributes.LIGHTNINGTHORNSCHANCE.get()) != null) {
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.TOPAZ_BOOTS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.05;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_TOPAZ_BOOTS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.1;
				crystallite_worn = true;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.TOPAZ_LEGGINGS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.05;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_TOPAZ_LEGGINGS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.1;
				crystallite_worn = true;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.TOPAZ_CHESTPLATE.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.05;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_TOPAZ_CHESTPLATE.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.1;
				crystallite_worn = true;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.TOPAZ_HELMET.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.05;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_TOPAZ_HELMET.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.1;
				crystallite_worn = true;
			}
			if (armor_pieces == 4) {
				if (crystallite_worn) {
					chance = chance + 0.1;
				} else {
					chance = chance + 0.05;
				}
			}
			if (IsInThunderstormProcedure.execute(world, x, y, z)) {
				if (crystallite_worn) {
					chance = chance * 1.5;
				} else {
					chance = chance * 2;
				}
			}
			((LivingEntity) entity).getAttribute(BetterToolsModAttributes.LIGHTNINGTHORNSCHANCE.get()).removeModifier((new AttributeModifier(UUID.fromString("d4a038c6-ffd6-4204-9729-a3487cbd49b2"), "", 0, AttributeModifier.Operation.ADDITION)));
			if (chance > 0) {
				if (entity instanceof LivingEntity && ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK) != null) {
					chance = chance + ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).getValue() * 0.05;
				}
				((LivingEntity) entity).getAttribute(BetterToolsModAttributes.LIGHTNINGTHORNSCHANCE.get())
						.addTransientModifier((new AttributeModifier(UUID.fromString("d4a038c6-ffd6-4204-9729-a3487cbd49b2"), "topaz_armor", chance, AttributeModifier.Operation.ADDITION)));
			}
		}
	}
}

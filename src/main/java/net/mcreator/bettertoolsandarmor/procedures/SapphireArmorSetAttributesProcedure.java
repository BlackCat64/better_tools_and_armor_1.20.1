package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

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
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

import java.util.UUID;

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
		if (entity instanceof LivingEntity && ((LivingEntity) entity).getAttribute(BetterToolsModAttributes.FREEZETHORNSCHANCE.get()) != null) {
			time = 100;
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.SAPPHIRE_BOOTS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.05;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SAPPHIRE_BOOTS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.1;
				time = 200;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.SAPPHIRE_LEGGINGS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.05;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SAPPHIRE_LEGGINGS.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.1;
				time = 200;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.SAPPHIRE_CHESTPLATE.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.05;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SAPPHIRE_CHESTPLATE.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.1;
				time = 200;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.SAPPHIRE_HELMET.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.05;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SAPPHIRE_HELMET.get()) {
				armor_pieces = armor_pieces + 1;
				chance = chance + 0.1;
				time = 200;
			}
			if (armor_pieces == 4) {
				if (time == 200) {
					chance = chance + 0.1;
				} else {
					chance = chance + 0.05;
				}
			}
			if (world.getBiome(BlockPos.containing(x, y, z)).value().getBaseTemperature() * 100f < 0.15) {
				if (time == 200) {
					chance = chance * 1.5;
					time = time * 1.5;
				} else {
					chance = chance * 2;
					time = time * 2;
				}
			}
			((LivingEntity) entity).getAttribute(BetterToolsModAttributes.FREEZETHORNSCHANCE.get()).removeModifier((new AttributeModifier(UUID.fromString("82308c34-6d1f-4840-8210-7f51700096a0"), "", 0, AttributeModifier.Operation.ADDITION)));
			((LivingEntity) entity).getAttribute(BetterToolsModAttributes.FREEZETHORNSTIME.get()).removeModifier((new AttributeModifier(UUID.fromString("9c6f8f03-e0c3-4602-850b-aa4f0bb7e509"), "", 0, AttributeModifier.Operation.ADDITION)));
			if (chance > 0) {
				if (entity instanceof LivingEntity && ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK) != null) {
					chance = chance + ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK).getValue() * 0.05;
				}
				if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.ICY_BRACELET.get(), lv).isPresent() : false) {
					if (world.getBiome(BlockPos.containing(x, y, z)).value().getBaseTemperature() * 100f < 0.15) {
						chance = chance + 0.1;
					} else {
						chance = chance + 0.05;
					}
				}
				((LivingEntity) entity).getAttribute(BetterToolsModAttributes.FREEZETHORNSCHANCE.get())
						.addTransientModifier((new AttributeModifier(UUID.fromString("82308c34-6d1f-4840-8210-7f51700096a0"), "sapphire_armor", chance, AttributeModifier.Operation.ADDITION)));
				((LivingEntity) entity).getAttribute(BetterToolsModAttributes.FREEZETHORNSTIME.get())
						.addTransientModifier((new AttributeModifier(UUID.fromString("9c6f8f03-e0c3-4602-850b-aa4f0bb7e509"), "sapphire_armor", time, AttributeModifier.Operation.ADDITION)));
			}
		}
	}
}

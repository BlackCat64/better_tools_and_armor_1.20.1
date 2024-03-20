package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class CrystalliteArmorRedstoneProcedureProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		double armor_pieces = 0;
		armor_pieces = 0;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_REDSTONE_HELMET.get()) {
			armor_pieces = armor_pieces + 1;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_REDSTONE_CHESTPLATE.get()) {
			armor_pieces = armor_pieces + 1;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_REDSTONE_LEGGINGS.get()) {
			armor_pieces = armor_pieces + 1;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_REDSTONE_BOOTS.get()) {
			armor_pieces = armor_pieces + 1;
		}
		if (armor_pieces == 4) {
			armor_pieces = armor_pieces + 1;
		}
		if (armor_pieces > 0) {
			((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).removeModifier(UUID.fromString("df870d1b-3909-4e49-b0d7-64645e5f6cf3"));
			((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
					.addTransientModifier((new AttributeModifier(UUID.fromString("df870d1b-3909-4e49-b0d7-64645e5f6cf3"), "crystallite_redstone_armor", (armor_pieces * 2), AttributeModifier.Operation.ADDITION)));
		} else {
			((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).removeModifier(UUID.fromString("df870d1b-3909-4e49-b0d7-64645e5f6cf3"));
		}
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteArmorGoldReachProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		double armor_pieces = 0;
		armor_pieces = 0;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:increased_reach_armor")))) {
			armor_pieces = armor_pieces + 1;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:increased_reach_armor")))) {
			armor_pieces = armor_pieces + 1;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:increased_reach_armor")))) {
			armor_pieces = armor_pieces + 1;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:increased_reach_armor")))) {
			armor_pieces = armor_pieces + 1;
		}
		if (armor_pieces == 4) {
			armor_pieces = armor_pieces + 1;
		}
		if (entity instanceof LivingEntity _entity) {
			_entity.getAttribute(Attributes.BLOCK_INTERACTION_RANGE).removeModifier(ResourceLocation.parse("better_tools:crystallite_gold_armor"));
		}
		if (armor_pieces > 0) {
			if (entity instanceof LivingEntity _entity) {
				AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:name"), armor_pieces, AttributeModifier.Operation.ADD_VALUE);
				if (!_entity.getAttribute(Attributes.BLOCK_INTERACTION_RANGE).hasModifier(modifier.id())) {
					_entity.getAttribute(Attributes.BLOCK_INTERACTION_RANGE).addPermanentModifier(modifier);
				}
			}
		}
	}
}
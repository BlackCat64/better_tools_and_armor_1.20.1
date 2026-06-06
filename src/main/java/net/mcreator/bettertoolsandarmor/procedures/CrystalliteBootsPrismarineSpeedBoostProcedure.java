package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteBootsPrismarineSpeedBoostProcedure {
	@SubscribeEvent
	public static void onEntityTick(EntityTickEvent.Pre event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_PRISMARINE_BOOTS.get()) {
			if (entity instanceof LivingEntity _entity) {
				AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:crystallite_boots_prismarine"), 0.5, AttributeModifier.Operation.ADD_VALUE);
				if (!_entity.getAttribute(NeoForgeMod.SWIM_SPEED).hasModifier(modifier.id())) {
					_entity.getAttribute(NeoForgeMod.SWIM_SPEED).addPermanentModifier(modifier);
				}
			}
			if (entity.isInWaterRainOrBubble() && !entity.isInWaterOrBubble()) {
				if (entity instanceof LivingEntity _entity) {
					AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:crystallite_boots_prismarine"), 0.03, AttributeModifier.Operation.ADD_VALUE);
					if (!_entity.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(modifier.id())) {
						_entity.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(modifier);
					}
				}
			} else {
				if (entity instanceof LivingEntity _entity) {
					_entity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(ResourceLocation.parse("better_tools:crystallite_boots_prismarine"));
				}
			}
		} else {
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(ResourceLocation.parse("better_tools:crystallite_boots_prismarine"));
			}
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(NeoForgeMod.SWIM_SPEED).removeModifier(ResourceLocation.parse("better_tools:crystallite_boots_prismarine"));
			}
		}
	}
}
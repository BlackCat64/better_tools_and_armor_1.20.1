package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CactusBodyProcedureProcedure {
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
		double damage = 0;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CACTUS_CHESTPLATE.get()) {
			damage = 2;
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.IRON_CACTUS_CHESTPLATE.get()) {
			damage = 3;
		} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.DIAMOND_CACTUS_CHESTPLATE.get()) {
			damage = 5;
		}
		if (entity instanceof LivingEntity _entity) {
			_entity.getAttribute(BetterToolsModAttributes.THORNS_DAMAGE).removeModifier(ResourceLocation.parse("better_tools:cactus_shirt"));
		}
		if (damage > 0) {
			if (entity instanceof LivingEntity _entity) {
				AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:cactus_shirt"), damage, AttributeModifier.Operation.ADD_VALUE);
				if (!_entity.getAttribute(BetterToolsModAttributes.THORNS_DAMAGE).hasModifier(modifier.id())) {
					_entity.getAttribute(BetterToolsModAttributes.THORNS_DAMAGE).addPermanentModifier(modifier);
				}
			}
		}
	}
}
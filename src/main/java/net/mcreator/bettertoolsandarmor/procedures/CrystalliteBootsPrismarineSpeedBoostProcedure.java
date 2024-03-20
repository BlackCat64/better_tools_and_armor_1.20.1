package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class CrystalliteBootsPrismarineSpeedBoostProcedure {
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
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_PRISMARINE_BOOTS.get()) {
			if (entity.isInWaterRainOrBubble()) {
				if (entity.isInWaterOrBubble()) {
					((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).removeModifier(UUID.fromString("b05b341a-9ba0-4b30-a013-4f827db2c03f"));
					if (!(((LivingEntity) entity).getAttribute(ForgeMod.SWIM_SPEED.get())
							.hasModifier((new AttributeModifier(UUID.fromString("4a803404-97f0-4299-b1c1-f5d9174d42f4"), "crystallite_boots_prismarine_water", 0.5, AttributeModifier.Operation.ADDITION)))))
						((LivingEntity) entity).getAttribute(ForgeMod.SWIM_SPEED.get())
								.addTransientModifier((new AttributeModifier(UUID.fromString("4a803404-97f0-4299-b1c1-f5d9174d42f4"), "crystallite_boots_prismarine_water", 0.5, AttributeModifier.Operation.ADDITION)));
				} else {
					((LivingEntity) entity).getAttribute(ForgeMod.SWIM_SPEED.get()).removeModifier(UUID.fromString("4a803404-97f0-4299-b1c1-f5d9174d42f4"));
					if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED)
							.hasModifier((new AttributeModifier(UUID.fromString("b05b341a-9ba0-4b30-a013-4f827db2c03f"), "crystallite_boots_prismarine_rain", 0.02, AttributeModifier.Operation.ADDITION)))))
						((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED)
								.addTransientModifier((new AttributeModifier(UUID.fromString("b05b341a-9ba0-4b30-a013-4f827db2c03f"), "crystallite_boots_prismarine_rain", 0.02, AttributeModifier.Operation.ADDITION)));
				}
			} else {
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).removeModifier(UUID.fromString("b05b341a-9ba0-4b30-a013-4f827db2c03f"));
				((LivingEntity) entity).getAttribute(ForgeMod.SWIM_SPEED.get()).removeModifier(UUID.fromString("4a803404-97f0-4299-b1c1-f5d9174d42f4"));
			}
		} else {
			((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).removeModifier(UUID.fromString("b05b341a-9ba0-4b30-a013-4f827db2c03f"));
			((LivingEntity) entity).getAttribute(ForgeMod.SWIM_SPEED.get()).removeModifier(UUID.fromString("4a803404-97f0-4299-b1c1-f5d9174d42f4"));
		}
	}
}

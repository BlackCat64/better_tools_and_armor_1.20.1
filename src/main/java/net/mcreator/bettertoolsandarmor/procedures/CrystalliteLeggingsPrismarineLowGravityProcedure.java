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
public class CrystalliteLeggingsPrismarineLowGravityProcedure {
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
		double gravity_reduction = 0;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_PRISMARINE_LEGGINGS.get()) {
			if (entity.isInWaterRainOrBubble()) {
				if (entity.isInWaterOrBubble()) {
					entity.setNoGravity(true);
				} else {
					((LivingEntity) entity).getAttribute(ForgeMod.ENTITY_GRAVITY.get()).removeModifier(UUID.fromString("6bf8ad9f-fc46-47b8-beee-6db7140caf6e"));
					((LivingEntity) entity).getAttribute(ForgeMod.ENTITY_GRAVITY.get())
							.addTransientModifier((new AttributeModifier(UUID.fromString("6bf8ad9f-fc46-47b8-beee-6db7140caf6e"), "crystallite_leggings_prismarine", (-0.5), AttributeModifier.Operation.MULTIPLY_BASE)));
					entity.setNoGravity(false);
				}
			} else {
				((LivingEntity) entity).getAttribute(ForgeMod.ENTITY_GRAVITY.get()).removeModifier(UUID.fromString("6bf8ad9f-fc46-47b8-beee-6db7140caf6e"));
				entity.setNoGravity(false);
			}
		} else {
			((LivingEntity) entity).getAttribute(ForgeMod.ENTITY_GRAVITY.get()).removeModifier(UUID.fromString("6bf8ad9f-fc46-47b8-beee-6db7140caf6e"));
			entity.setNoGravity(false);
		}
	}
}

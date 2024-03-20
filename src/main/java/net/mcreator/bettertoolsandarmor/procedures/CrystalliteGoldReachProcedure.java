package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class CrystalliteGoldReachProcedure {
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
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:increased_reach_tools")))) {
			if (!(((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).hasModifier((new AttributeModifier(UUID.fromString("5e4c476e-fefb-45d9-ac4d-5642ad234de6"), "crystallite_gold_tool", 3, AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get())
						.addTransientModifier((new AttributeModifier(UUID.fromString("5e4c476e-fefb-45d9-ac4d-5642ad234de6"), "crystallite_gold_tool", 3, AttributeModifier.Operation.ADDITION)));
		} else {
			((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).removeModifier(UUID.fromString("5e4c476e-fefb-45d9-ac4d-5642ad234de6"));
		}
	}
}

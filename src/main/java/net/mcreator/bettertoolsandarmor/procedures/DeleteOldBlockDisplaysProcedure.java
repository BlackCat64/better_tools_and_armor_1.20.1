package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;

import javax.annotation.Nullable;

@EventBusSubscriber
public class DeleteOldBlockDisplaysProcedure {
	@SubscribeEvent
	public static void onEntityTick(EntityTickEvent.Pre event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(BetterToolsModMobEffects.FROZEN))) {
			DeleteEntityIceBlockDisplayProcedure.execute(world, entity, true);
		}
		if (!(entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(BetterToolsModMobEffects.PITFALL))) {
			DeleteEntityMudBlockDisplayProcedure.execute(world, entity);
		}
	}
}
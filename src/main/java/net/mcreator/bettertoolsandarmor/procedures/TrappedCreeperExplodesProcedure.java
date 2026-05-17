package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.EntityMobGriefingEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;

import javax.annotation.Nullable;

@EventBusSubscriber
public class TrappedCreeperExplodesProcedure {
	@SubscribeEvent
	public static void onEntityGrief(EntityMobGriefingEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Creeper && entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(BetterToolsModMobEffects.PITFALL)) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(BetterToolsModMobEffects.PITFALL);
			DeleteEntityPitfallBlockDisplayProcedure.execute(entity);
		}
	}
}

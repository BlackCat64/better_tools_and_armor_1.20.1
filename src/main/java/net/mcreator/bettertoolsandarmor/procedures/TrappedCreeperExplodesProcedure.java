package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;

import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
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
		if (entity instanceof Creeper && entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(BetterToolsModMobEffects.PITFALL.get())) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(BetterToolsModMobEffects.PITFALL.get());
			DeleteEntityPitfallBlockDisplayProcedure.execute(entity);
		}
	}
}

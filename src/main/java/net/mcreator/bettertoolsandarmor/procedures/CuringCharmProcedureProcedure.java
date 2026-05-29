package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CuringCharmProcedureProcedure {
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
		double shorten_time = 0;
		shorten_time = 200;
		if (true) {
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.BLINDNESS) ? _livEnt.getEffect(MobEffects.BLINDNESS).getDuration() : 0) <= shorten_time) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.BLINDNESS);
			}
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.DARKNESS) ? _livEnt.getEffect(MobEffects.DARKNESS).getDuration() : 0) <= shorten_time) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.DARKNESS);
			}
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.HUNGER) ? _livEnt.getEffect(MobEffects.HUNGER).getDuration() : 0) <= shorten_time
					* ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.HUNGER) ? _livEnt.getEffect(MobEffects.HUNGER).getAmplifier() : 0) + 1)) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.HUNGER);
			}
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.DIG_SLOWDOWN) ? _livEnt.getEffect(MobEffects.DIG_SLOWDOWN).getDuration() : 0) <= shorten_time
					* ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.DIG_SLOWDOWN) ? _livEnt.getEffect(MobEffects.DIG_SLOWDOWN).getAmplifier() : 0) + 1)) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.DIG_SLOWDOWN);
			}
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.CONFUSION) ? _livEnt.getEffect(MobEffects.CONFUSION).getDuration() : 0) <= shorten_time
					* ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.CONFUSION) ? _livEnt.getEffect(MobEffects.CONFUSION).getAmplifier() : 0) + 1)) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.CONFUSION);
			}
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.POISON) ? _livEnt.getEffect(MobEffects.POISON).getDuration() : 0) <= shorten_time
					* ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.POISON) ? _livEnt.getEffect(MobEffects.POISON).getAmplifier() : 0) + 1)) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.POISON);
			}
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.MOVEMENT_SLOWDOWN) ? _livEnt.getEffect(MobEffects.MOVEMENT_SLOWDOWN).getDuration() : 0) <= shorten_time
					* ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.MOVEMENT_SLOWDOWN) ? _livEnt.getEffect(MobEffects.MOVEMENT_SLOWDOWN).getAmplifier() : 0) + 1)) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
			}
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.WEAKNESS) ? _livEnt.getEffect(MobEffects.WEAKNESS).getDuration() : 0) <= shorten_time
					* ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.WEAKNESS) ? _livEnt.getEffect(MobEffects.WEAKNESS).getAmplifier() : 0) + 1)) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.WEAKNESS);
			}
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.WITHER) ? _livEnt.getEffect(MobEffects.WITHER).getDuration() : 0) <= (shorten_time / 2)
					* ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.WITHER) ? _livEnt.getEffect(MobEffects.WITHER).getAmplifier() : 0) + 1)) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.WITHER);
			}
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(BetterToolsModMobEffects.FROZEN) ? _livEnt.getEffect(BetterToolsModMobEffects.FROZEN).getDuration() : 0) <= shorten_time / 2) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(BetterToolsModMobEffects.FROZEN);
			}
		}
	}
}
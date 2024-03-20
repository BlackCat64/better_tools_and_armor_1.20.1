package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CuringCharmProcedureProcedure {
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
		double shorten_time = 0;
		shorten_time = 200;
		if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.CURING_CHARM.get(), lv).isPresent() : false) {
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
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(BetterToolsModMobEffects.FROZEN.get()) ? _livEnt.getEffect(BetterToolsModMobEffects.FROZEN.get()).getDuration() : 0) <= shorten_time / 2) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(BetterToolsModMobEffects.FROZEN.get());
			}
		}
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class FrozenEffectStopJumpingProcedure {
	@SubscribeEvent
	public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		double time = 0;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(BetterToolsModMobEffects.FROZEN.get())) {
			if (entity instanceof Player && !(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
				time = entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(BetterToolsModMobEffects.FROZEN.get()) ? _livEnt.getEffect(BetterToolsModMobEffects.FROZEN.get()).getDuration() : 0;
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(BetterToolsModMobEffects.FROZEN.get());
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(BetterToolsModMobEffects.FROZEN.get(), (int) (time - 10), 0, true, false));
			}
		}
	}
}

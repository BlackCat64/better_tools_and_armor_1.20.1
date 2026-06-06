package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;

import javax.annotation.Nullable;

@EventBusSubscriber
public class FrozenEffectStopJumpingProcedure {
	@SubscribeEvent
	public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double time = 0;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(BetterToolsModMobEffects.FROZEN) && entity instanceof Player && !(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
			time = entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(BetterToolsModMobEffects.FROZEN) ? _livEnt.getEffect(BetterToolsModMobEffects.FROZEN).getDuration() : 0;
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(BetterToolsModMobEffects.FROZEN);
			if (time > 20) {
				DeleteEntityIceBlockDisplayProcedure.execute(world, entity, false);
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(BetterToolsModMobEffects.FROZEN, (int) (time - 20), 0, true, false));
			}
		}
	}
}
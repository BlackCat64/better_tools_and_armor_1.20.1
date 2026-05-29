package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import javax.annotation.Nullable;

@EventBusSubscriber
public class WitheredGauntletProcedureProcedure {
	@SubscribeEvent
	public static void onPlayerCriticalHit(CriticalHitEvent event) {
		execute(event, event.getTarget(), event.isVanillaCritical());
	}

	public static void execute(Entity entity, boolean isvanillacritical) {
		execute(null, entity, isvanillacritical);
	}

	private static void execute(@Nullable Event event, Entity entity, boolean isvanillacritical) {
		if (entity == null)
			return;
		if (isvanillacritical) {
			if (true) {
				if (!(entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(MobEffects.WITHER))) {
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 2, false, true));
				}
			}
		}
	}
}
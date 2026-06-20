package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

@EventBusSubscriber
public class SapphireArmorFreezeProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingDamageEvent.Pre event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(Entity entity, Entity sourceentity) {
		execute(null, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		double armor_pieces = 0;
		double time = 0;
		double chance = 0;
		if (Math.random() < (entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(BetterToolsModAttributes.FREEZE_THORNS_CHANCE)
				? _livingEntity0.getAttribute(BetterToolsModAttributes.FREEZE_THORNS_CHANCE).getValue()
				: 0)) {
			if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(BetterToolsModMobEffects.FROZEN,
						(int) (entity instanceof LivingEntity _livingEntity1 && _livingEntity1.getAttributes().hasAttribute(BetterToolsModAttributes.FREEZE_THORNS_TIME)
								? _livingEntity1.getAttribute(BetterToolsModAttributes.FREEZE_THORNS_TIME).getValue()
								: 0),
						0, false, true));
		}
	}
}
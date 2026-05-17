package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteSwordSkyCritTrackerProcedure {
	@SubscribeEvent
	public static void onPlayerCriticalHit(CriticalHitEvent event) {
		execute(event, event.getEntity().level(), event.getTarget(), event.getEntity(), event.isVanillaCritical());
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity, boolean isvanillacritical) {
		execute(null, world, entity, sourceentity, isvanillacritical);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity, boolean isvanillacritical) {
		if (entity == null || sourceentity == null)
			return;
		if (isvanillacritical) {
			{
				BetterToolsModVariables.PlayerVariables _vars = sourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.critical_hit = true;
				_vars.syncPlayerVariables(sourceentity);
			}
			if ((entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER)
					? _livingEntity0.getAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER).getValue()
					: 0) != 1.5) {
				if (event instanceof CriticalHitEvent _event)
					_event.setCriticalHit(false);
			}
			BetterToolsMod.queueServerWork(1, () -> {
				{
					BetterToolsModVariables.PlayerVariables _vars = sourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
					_vars.critical_hit = false;
					_vars.syncPlayerVariables(sourceentity);
				}
			});
		}
	}
}

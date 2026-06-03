package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CritTrackerProcedure {
	@SubscribeEvent
	public static void onPlayerCriticalHit(CriticalHitEvent event) {
		execute(event, event.getEntity().level(), event.getEntity(), event.isVanillaCritical());
	}

	public static void execute(LevelAccessor world, Entity sourceentity, boolean isvanillacritical) {
		execute(null, world, sourceentity, isvanillacritical);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity sourceentity, boolean isvanillacritical) {
		if (sourceentity == null)
			return;
		if (isvanillacritical) {
			{
				BetterToolsModVariables.PlayerVariables _vars = sourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.critical_hit = true;
				_vars.markSyncDirty();
			}
			if ((sourceentity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER)
					? _livingEntity0.getAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER).getValue()
					: 0) != 1.5) {
				if (event instanceof CriticalHitEvent _event) {
					_event.setDamageMultiplier((float) (sourceentity instanceof LivingEntity _livingEntity1 && _livingEntity1.getAttributes().hasAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER)
							? _livingEntity1.getAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER).getValue()
							: 0));
				}
			}
			if ((sourceentity instanceof LivingEntity _livingEntity2 && _livingEntity2.getAttributes().hasAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER)
					? _livingEntity2.getAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER).getValue()
					: 0) >= 2.5) {
				if (sourceentity instanceof ServerPlayer _player) {
					AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:high_crit_multiplier_adv"));
					if (_adv != null) {
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
			}
			BetterToolsMod.queueServerWork(1, () -> {
				{
					BetterToolsModVariables.PlayerVariables _vars = sourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
					_vars.critical_hit = false;
					_vars.markSyncDirty();
				}
			});
		}
	}
}
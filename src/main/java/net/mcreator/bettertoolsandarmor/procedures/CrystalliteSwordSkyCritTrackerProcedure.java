package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteSwordSkyCritTrackerProcedure {
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
				boolean _setval = true;
				sourceentity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.critical_hit = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
			if (((LivingEntity) sourceentity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get()).getValue() != 1.5) {
				if (event != null && event.hasResult()) {
					event.setResult(Event.Result.DENY);
				}
			}
			BetterToolsMod.queueServerWork(10, () -> {
				{
					boolean _setval = false;
					sourceentity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.critical_hit = _setval;
						capability.syncPlayerVariables(sourceentity);
					});
				}
			});
		}
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class DeathLocationTrackerProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
		}
	}

	public static void execute(double x, double y, double z, Entity entity) {
		execute(null, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player) {
			{
				double _setval = x;
				entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.last_death_x = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = y;
				entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.last_death_y = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = z;
				entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.last_death_z = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}

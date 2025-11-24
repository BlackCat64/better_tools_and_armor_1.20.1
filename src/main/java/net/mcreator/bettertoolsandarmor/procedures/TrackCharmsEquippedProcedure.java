package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class TrackCharmsEquippedProcedure {
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
		double num = 0;
		num = CuriosApi.getCuriosInventory(player).map(inv -> {
			int count = 0;
			// Iterate over each curio slot
			for (var handler : inv.getCurios().values()) {
				for (int i = 0; i < handler.getSlots(); i++) {
					if (!handler.getStackInSlot(i).isEmpty()) {
						count++;
					}
				}
			}
			return count;
		}).orElse(0);
		{
			double _setval = num;
			entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.charms_equipped = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}

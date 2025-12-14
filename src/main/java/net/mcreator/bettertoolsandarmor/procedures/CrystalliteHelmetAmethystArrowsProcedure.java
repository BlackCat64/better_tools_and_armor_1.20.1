package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteHelmetAmethystArrowsProcedure {
	@SubscribeEvent
	public static void onUseItemStop(LivingEntityUseItemEvent.Stop event) {
		if (event != null && event.getEntity() != null) {
			execute(event);
		}
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		boolean shot = false;
		double distance = 0;
		double raycast = 0;
		double speed = 0;
		double charge_time = 0;
		double damage = 0;
		double base_damage = 0;
	}
}

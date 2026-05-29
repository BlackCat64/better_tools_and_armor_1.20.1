package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteBowHoneyPreventReboundProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingIncomingDamageEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getSource().getDirectEntity());
		}
	}

	public static void execute(Entity immediatesourceentity) {
		execute(null, immediatesourceentity);
	}

	private static void execute(@Nullable Event event, Entity immediatesourceentity) {
		if (immediatesourceentity == null)
			return;
		if (immediatesourceentity.getPersistentData().getBoolean("crystallite_honey_upgrade")) {
			if (!immediatesourceentity.level().isClientSide())
				immediatesourceentity.discard();
		}
	}
}
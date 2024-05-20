package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class DetectArrowHittingEntityProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getSource().getDirectEntity());
		}
	}

	public static void execute(Entity immediatesourceentity) {
		execute(null, immediatesourceentity);
	}

	private static void execute(@Nullable Event event, Entity immediatesourceentity) {
		if (immediatesourceentity == null)
			return;
		if (immediatesourceentity instanceof Arrow) {
			immediatesourceentity.getPersistentData().putBoolean("hitting_entity", true);
		}
	}
}

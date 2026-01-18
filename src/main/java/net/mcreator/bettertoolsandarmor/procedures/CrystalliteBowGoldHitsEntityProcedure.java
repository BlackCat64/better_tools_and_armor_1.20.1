package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteBowGoldHitsEntityProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getSource().getDirectEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, Entity immediatesourceentity, Entity sourceentity) {
		execute(null, world, immediatesourceentity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity immediatesourceentity, Entity sourceentity) {
		if (immediatesourceentity == null || sourceentity == null)
			return;
		if (immediatesourceentity instanceof Arrow && immediatesourceentity.getPersistentData().getBoolean("crystallite_gold_upgrade")) {
			CrystalliteBowGoldFireworkProcedure.execute(world, immediatesourceentity.getX(), immediatesourceentity.getY(), immediatesourceentity.getZ(), sourceentity);
			if (!immediatesourceentity.level().isClientSide())
				immediatesourceentity.discard();
		}
	}
}

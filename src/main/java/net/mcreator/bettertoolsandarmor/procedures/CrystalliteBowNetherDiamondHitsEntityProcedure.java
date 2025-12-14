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
public class CrystalliteBowNetherDiamondHitsEntityProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getSource().getDirectEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity immediatesourceentity) {
		execute(null, world, x, y, z, immediatesourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity immediatesourceentity) {
		if (immediatesourceentity == null)
			return;
		if (immediatesourceentity instanceof Arrow && immediatesourceentity.getPersistentData().getBoolean("crystallite_nether_diamond_upgrade")) {
			if (!immediatesourceentity.isInWaterRainOrBubble()) {
				ArrowExplosionProcedure.execute(world, x, y, z, immediatesourceentity);
			}
			if (!immediatesourceentity.level().isClientSide())
				immediatesourceentity.discard();
		}
	}
}

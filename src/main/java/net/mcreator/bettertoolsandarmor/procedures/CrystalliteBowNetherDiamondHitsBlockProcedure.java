package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class CrystalliteBowNetherDiamondHitsBlockProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity arrow = null;
		String uuid = "";
		String potion = "";
		if (entity instanceof ArmorStand && entity.getPersistentData().getBoolean("crystallite_bow_nether_diamond")) {
			uuid = entity.getPersistentData().getString("arrow");
			if (!(uuid).isEmpty()) {
				if (world instanceof ServerLevel serverLevel) {
					arrow = serverLevel.getEntity(UUID.fromString(uuid));
				}
				if (!(arrow == null)) {
					{
						Entity _ent = entity;
						_ent.teleportTo((arrow.getX()), (arrow.getY()), (arrow.getZ()));
						if (_ent instanceof ServerPlayer _serverPlayer)
							_serverPlayer.connection.teleport((arrow.getX()), (arrow.getY()), (arrow.getZ()), _ent.getYRot(), _ent.getXRot());
					}
					if (GetEntityLogicDataProcedure.execute(arrow, "inGround")) {
						if (!entity.level().isClientSide())
							entity.discard();
						if (!arrow.isInWaterRainOrBubble()) {
							ArrowExplosionProcedure.execute(world, x, y, z, arrow);
							if (!arrow.level().isClientSide())
								arrow.discard();
						}
					}
				} else {
					if (!entity.level().isClientSide())
						entity.discard();
				}
			} else {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
	}
}

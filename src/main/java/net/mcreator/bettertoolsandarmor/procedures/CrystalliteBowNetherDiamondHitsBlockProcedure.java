package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

import java.util.UUID;

@EventBusSubscriber
public class CrystalliteBowNetherDiamondHitsBlockProcedure {
	@SubscribeEvent
	public static void onEntityTick(EntityTickEvent.Pre event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity arrow = null;
		Entity player = null;
		String uuid = "";
		String playerUUID = "";
		if (entity instanceof ArmorStand && entity.getPersistentData().getBoolean("crystallite_bow_nether_diamond")) {
			uuid = entity.getPersistentData().getString("arrow");
			arrow = world instanceof ServerLevel _level3 ? getEntityFromUUID(_level3, uuid) : null;
			if (arrow instanceof Arrow) {
				{
					Entity _ent = entity;
					_ent.teleportTo((arrow.getX()), (arrow.getY()), (arrow.getZ()));
					if (_ent instanceof ServerPlayer _serverPlayer)
						_serverPlayer.connection.teleport((arrow.getX()), (arrow.getY()), (arrow.getZ()), _ent.getYRot(), _ent.getXRot());
				}
				if (GetEntityLogicDataProcedure.execute(arrow, "inGround")) {
					playerUUID = entity.getPersistentData().getString("player");
					player = world instanceof ServerLevel _level10 ? getEntityFromUUID(_level10, playerUUID) : null;
					if (player instanceof LivingEntity && !arrow.isInWaterRainOrBubble()) {
						ArrowExplosionProcedure.execute(world, x, y, z, arrow, entity, player);
						if (!arrow.level().isClientSide())
							arrow.discard();
					}
					if (!entity.level().isClientSide())
						entity.discard();
				}
			} else {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
	}

	private static Entity getEntityFromUUID(ServerLevel level, String uuid) {
		try {
			return level.getEntity(UUID.fromString(uuid));
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import java.util.UUID;

@EventBusSubscriber
public class ThunderShotLightningRodProcedure {
	@SubscribeEvent
	public static void onEntityTick(EntityTickEvent.Pre event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		Entity arrow = null;
		String player_uuid = "";
		String uuid = "";
		if (entity instanceof ArmorStand && entity.getPersistentData().getBoolean("thunder_shot")) {
			uuid = entity.getPersistentData().getString("arrow");
			arrow = world instanceof ServerLevel _level3 ? getEntityFromUUID(_level3, uuid) : null;
			if (arrow instanceof Arrow) {
				arrow.getPersistentData().putBoolean("being_tracked", true);
				{
					Entity _ent = entity;
					_ent.teleportTo((arrow.getX()), (arrow.getY()), (arrow.getZ()));
					if (_ent instanceof ServerPlayer _serverPlayer)
						_serverPlayer.connection.teleport((arrow.getX()), (arrow.getY()), (arrow.getZ()), _ent.getYRot(), _ent.getXRot());
				}
				if (GetEntityLogicDataProcedure.execute(arrow, "inGround")) {
					if ((GetEntityTextDataInListProcedure.execute(arrow, "inBlockState", "Name")).equals("minecraft:lightning_rod")) {
						if (world instanceof ServerLevel _level) {
							Entity entityToSpawn = EntityType.LIGHTNING_BOLT.spawn(_level, BlockPos.containing(arrow.getX(), arrow.getY(), arrow.getZ()), MobSpawnType.MOB_SUMMONED);
							if (entityToSpawn != null) {
								entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
							}
						}
						if (!entity.level().isClientSide())
							entity.discard();
					}
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
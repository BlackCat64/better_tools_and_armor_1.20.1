package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

import java.util.UUID;
import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class CrystalliteBowAmethystHomingProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		String uuid = "";
		String potion = "";
		Entity arrow = null;
		Entity nearest = null;
		if (entity instanceof ArmorStand && entity.getPersistentData().getBoolean("crystallite_bow_amethyst")) {
			uuid = entity.getPersistentData().getString("arrow");
			if (!(uuid).isEmpty()) {
				if (world instanceof ServerLevel serverLevel) {
					arrow = serverLevel.getEntity(UUID.fromString(uuid));
				}
				if (arrow == null || GetEntityLogicDataProcedure.execute(arrow, "inGround")) {
					if (!entity.level().isClientSide())
						entity.discard();
				} else {
					{
						Entity _ent = entity;
						_ent.teleportTo((arrow.getX()), (arrow.getY()), (arrow.getZ()));
						if (_ent instanceof ServerPlayer _serverPlayer)
							_serverPlayer.connection.teleport((arrow.getX()), (arrow.getY()), (arrow.getZ()), _ent.getYRot(), _ent.getXRot());
					}
					{
						final Vec3 _center = new Vec3((arrow.getX()), (arrow.getY()), (arrow.getZ()));
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (!(entityiterator == null || (entityiterator.getStringUUID()).equals(entity.getPersistentData().getString("player"))) && (entityiterator instanceof Mob || entityiterator instanceof Player)
									&& !entityiterator.isInvisible()) {
								ArrowHomingProcedureProcedure.execute(world, arrow, entityiterator);
								HomingArrowParticlesProcedure.execute(world, arrow.getX(), arrow.getY(), arrow.getZ(), entityiterator.getX(), entityiterator.getY() + entity.getBbHeight() / 2, entityiterator.getZ());
								if (!entity.level().isClientSide())
									entity.discard();
								break;
							}
						}
					}
				}
			} else {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
	}
}

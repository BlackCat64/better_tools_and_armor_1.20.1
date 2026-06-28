package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import javax.annotation.Nullable;

import java.util.UUID;
import java.util.Comparator;

@EventBusSubscriber
public class CrystalliteBowAmethystHomingProcedure {
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
		Entity nearest = null;
		Entity player = null;
		String uuid = "";
		String player_uuid = "";
		if (entity instanceof ArmorStand && entity.getPersistentData().getDouble("homing_radius") > 0) {
			uuid = entity.getPersistentData().getString("arrow");
			player_uuid = entity.getPersistentData().getString("player");
			arrow = world instanceof ServerLevel _level4 ? getEntityFromUUID(_level4, uuid) : null;
			player = world instanceof ServerLevel _level5 ? getEntityFromUUID(_level5, player_uuid) : null;
			if (!(arrow instanceof Arrow) || !(player instanceof LivingEntity) || GetEntityLogicDataProcedure.execute(arrow, "inGround")) {
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
					for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((entity.getPersistentData().getDouble("homing_radius") * 2) / 2d), e -> true).stream()
							.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
						if (!(entityiterator == player) && (entityiterator instanceof Mob || entityiterator instanceof Player) && !entityiterator.isInvisible()) {
							ArrowHomingProcedureProcedure.execute(world, arrow, entityiterator);
							HomingArrowParticlesProcedure.execute(world, arrow.getX(), arrow.getY(), arrow.getZ(), entityiterator.getX(), entityiterator.getY() + entityiterator.getBbHeight() / 2, entityiterator.getZ());
							if (!entity.level().isClientSide())
								entity.discard();
							if ((player != null ? entityiterator.distanceTo(player) : -1) >= 30) {
								if (player instanceof ServerPlayer _player) {
									AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:aimbot_adv"));
									if (_adv != null) {
										AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
										if (!_ap.isDone()) {
											for (String criteria : _ap.getRemainingCriteria())
												_player.getAdvancements().award(_adv, criteria);
										}
									}
								}
							}
							break;
						}
					}
				}
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
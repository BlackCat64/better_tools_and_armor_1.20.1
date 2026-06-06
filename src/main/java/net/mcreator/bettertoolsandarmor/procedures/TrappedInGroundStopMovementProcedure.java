package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Display;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;

import java.util.UUID;

public class TrappedInGroundStopMovementProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		Entity display = null;
		if (entity.onGround() && !(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false) && GetDistanceBetweenPointsProcedure.execute(entity.getX(), entity.getY(), entity.getZ(), entity.getPersistentData().getDouble("frozen_at_x"),
				entity.getPersistentData().getDouble("frozen_at_y"), entity.getPersistentData().getDouble("frozen_at_z")) <= 2) {
			entity.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0.25, 0.05, 0.25));
			entity.setDeltaMovement(new Vec3(0, (-2), 0));
			display = world instanceof ServerLevel _level11 ? getEntityFromUUID(_level11, (entity.getPersistentData().getString("pitfall_block_display"))) : null;
			if (display instanceof Display.BlockDisplay) {
				{
					Entity _ent = display;
					_ent.teleportTo((entity.getX()), (entity.getY()), (entity.getZ()));
					if (_ent instanceof ServerPlayer _serverPlayer)
						_serverPlayer.connection.teleport((entity.getX()), (entity.getY()), (entity.getZ()), _ent.getYRot(), _ent.getXRot());
				}
				{
					Entity _ent = display;
					_ent.setYRot(0);
					_ent.setXRot(0);
					_ent.setYBodyRot(_ent.getYRot());
					_ent.setYHeadRot(_ent.getYRot());
					_ent.yRotO = _ent.getYRot();
					_ent.xRotO = _ent.getXRot();
					if (_ent instanceof LivingEntity _entity) {
						_entity.yBodyRotO = _entity.getYRot();
						_entity.yHeadRotO = _entity.getYRot();
					}
				}
			}
		} else {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(BetterToolsModMobEffects.PITFALL);
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
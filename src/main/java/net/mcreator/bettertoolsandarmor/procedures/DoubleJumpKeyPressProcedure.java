package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.Minecraft;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;

public class DoubleJumpKeyPressProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayer _serverPlayer) {
					return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
				} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
					return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
				}
				return false;
			}
		}.checkGamemode(entity))) {
			if (!entity.onGround() && entity.getDeltaMovement().y() < 0.5) {
				if (!entity.isSwimming()) {
					if (entity instanceof LivingEntity _livEnt4 && _livEnt4.hasEffect(BetterToolsModMobEffects.DOUBLE_JUMP.get())
							&& (entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).extra_jumps > 0) {
						if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).time_since_last_jumped >= 4) {
							entity.fallDistance = 0;
							entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), 0.5, (entity.getDeltaMovement().z())));
							{
								double _setval = (entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).extra_jumps - 1;
								entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.extra_jumps = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
						}
					}
				}
			}
		}
	}
}

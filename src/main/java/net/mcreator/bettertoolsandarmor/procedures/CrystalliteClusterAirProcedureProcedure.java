package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.Minecraft;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModBlocks;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteClusterAirProcedureProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double found_x = 0;
		double found_y = 0;
		double found_z = 0;
		if (!(getEntityGameType(entity) == GameType.SPECTATOR)) {
			if ((world.getBlockState(
					new BlockPos(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4)), ClipContext.Block.VISUAL, ClipContext.Fluid.ANY, entity)).getBlockPos().getX(),
							entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4)), ClipContext.Block.VISUAL, ClipContext.Fluid.ANY, entity)).getBlockPos().getY(),
							entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4)), ClipContext.Block.VISUAL, ClipContext.Fluid.ANY, entity)).getBlockPos().getZ())))
					.getBlock() == BetterToolsModBlocks.CRYSTALLITE_CLUSTER_AIR.get()) {
				RemoveCrystalliteClusterAirProcedure.execute(world,
						entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(16)), ClipContext.Block.VISUAL, ClipContext.Fluid.ANY, entity)).getBlockPos().getX(),
						entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(16)), ClipContext.Block.VISUAL, ClipContext.Fluid.ANY, entity)).getBlockPos().getY(),
						entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(16)), ClipContext.Block.VISUAL, ClipContext.Fluid.ANY, entity)).getBlockPos().getZ());
			} else if (BetterToolsModVariables.MapVariables.get(world).crystallite_shimmer_timer == 0) {
				found = false;
				BetterToolsModVariables.MapVariables.get(world).crystallite_shimmer_timer = 200;
				BetterToolsModVariables.MapVariables.get(world).markSyncDirty();
				sx = -40;
				for (int index0 = 0; index0 < 80; index0++) {
					sy = -40;
					for (int index1 = 0; index1 < 80; index1++) {
						sz = -40;
						for (int index2 = 0; index2 < 80; index2++) {
							if (!found && (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BetterToolsModBlocks.CRYSTALLITE_CLUSTER_AIR.get()) {
								found = true;
								found_x = x + sx;
								found_y = y + sy;
								found_z = z + sz;
							}
							sz = sz + 1;
						}
						sy = sy + 1;
					}
					sx = sx + 1;
				}
				if (found && y < 60) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(found_x, found_y, found_z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("better_tools:crystallite_shimmer")), SoundSource.NEUTRAL, 10, 1);
						} else {
							_level.playLocalSound(found_x, found_y, found_z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("better_tools:crystallite_shimmer")), SoundSource.NEUTRAL, 10, 1, false);
						}
					}
				}
			}
		}
	}

	private static GameType getEntityGameType(Entity entity) {
		if (entity instanceof ServerPlayer serverPlayer) {
			return serverPlayer.gameMode.getGameModeForPlayer();
		} else if (entity instanceof Player player && player.level().isClientSide()) {
			PlayerInfo playerInfo = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());
			if (playerInfo != null)
				return playerInfo.getGameMode();
		}
		return null;
	}
}
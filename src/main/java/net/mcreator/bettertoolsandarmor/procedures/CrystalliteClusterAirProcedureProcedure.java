package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModBlocks;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteClusterAirProcedureProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
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
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BetterToolsModBlocks.CRYSTALLITE_CLUSTER_AIR.get()
				|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == BetterToolsModBlocks.CRYSTALLITE_CLUSTER_AIR.get()
				|| (world.getBlockState(
						new BlockPos(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(16)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX(),
								entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(16)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY(),
								entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(16)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())))
						.getBlock() == BetterToolsModBlocks.CRYSTALLITE_CLUSTER_AIR.get()) {
			sx = -8;
			for (int index0 = 0; index0 < 16; index0++) {
				sy = -8;
				for (int index1 = 0; index1 < 16; index1++) {
					sz = -8;
					for (int index2 = 0; index2 < 16; index2++) {
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BetterToolsModBlocks.CRYSTALLITE_CLUSTER_AIR.get()) {
							world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), Blocks.AIR.defaultBlockState(), 3);
						}
						sz = sz + 1;
					}
					sy = sy + 1;
				}
				sx = sx + 1;
			}
		} else {
			if (world.dayTime() % 200 == 0) {
				found = false;
				sx = -40;
				for (int index3 = 0; index3 < 80; index3++) {
					sy = -40;
					for (int index4 = 0; index4 < 80; index4++) {
						sz = -40;
						for (int index5 = 0; index5 < 80; index5++) {
							if (found == false) {
								if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BetterToolsModBlocks.CRYSTALLITE_CLUSTER_AIR.get()) {
									found = true;
									found_x = x + sx;
									found_y = y + sy;
									found_z = z + sz;
								}
							}
							sz = sz + 1;
						}
						sy = sy + 1;
					}
					sx = sx + 1;
				}
				if (found == true && y < 60) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(found_x, found_y, found_z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("better_tools:crystallite_shimmer")), SoundSource.BLOCKS, 10, 1);
						} else {
							_level.playLocalSound(found_x, found_y, found_z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("better_tools:crystallite_shimmer")), SoundSource.BLOCKS, 10, 1, false);
						}
					}
				}
			}
		}
	}
}

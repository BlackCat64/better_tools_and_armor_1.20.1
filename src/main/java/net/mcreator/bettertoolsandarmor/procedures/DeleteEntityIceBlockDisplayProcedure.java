package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Display;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import java.util.UUID;

public class DeleteEntityIceBlockDisplayProcedure {
	public static void execute(LevelAccessor world, Entity entity, boolean particles) {
		if (entity == null)
			return;
		Entity display = null;
		display = world instanceof ServerLevel _level1 ? getEntityFromUUID(_level1, (entity.getPersistentData().getString("frozen_block_display"))) : null;
		if (display instanceof Display.BlockDisplay) {
			if (!display.level().isClientSide())
				display.discard();
			if (particles) {
				world.levelEvent(2001, BlockPos.containing(display.getX(), display.getY() + display.getBbHeight() / 2d, display.getZ()), Block.getId(Blocks.ICE.defaultBlockState()));
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(display.getX(), display.getY(), display.getZ()), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.glass.break")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound((display.getX()), (display.getY()), (display.getZ()), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.glass.break")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.SNOWFLAKE, (display.getX()), (display.getY() + display.getBbHeight() / 2d), (display.getZ()), 5, 0.6, 0.6, 0.6, 0.1);
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
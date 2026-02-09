package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

public class CriticalHitParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, double amount) {
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.CRIT, x, (y + 1), z, 10, 0.5, 1, 0.5, 0.05);
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.DAMAGE_INDICATOR, x, (y + 1), z, (int) (amount / 2), 0.4, 0.6, 0.4, 0.025);
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.crit")), SoundSource.PLAYERS, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.crit")), SoundSource.PLAYERS, 1, 1, false);
			}
		}
	}
}

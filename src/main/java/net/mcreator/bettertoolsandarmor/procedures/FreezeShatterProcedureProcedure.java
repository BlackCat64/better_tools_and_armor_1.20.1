package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;

import javax.annotation.Nullable;

@EventBusSubscriber
public class FreezeShatterProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingIncomingDamageEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getSource(), event.getEntity(), event.getSource().getDirectEntity(), event.getSource().getEntity(),
					event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, Entity immediatesourceentity, Entity sourceentity, double amount) {
		execute(null, world, x, y, z, damagesource, entity, immediatesourceentity, sourceentity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, Entity immediatesourceentity, Entity sourceentity, double amount) {
		if (damagesource == null || entity == null || immediatesourceentity == null || sourceentity == null)
			return;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(BetterToolsModMobEffects.FROZEN) && entity.getPersistentData().getDouble("frozen_ticks") >= 5
				&& !damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("better_tools:freeze_shatter_damage")))) {
			if (immediatesourceentity instanceof Arrow) {
				if (!immediatesourceentity.level().isClientSide())
					immediatesourceentity.discard();
			}
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("better_tools:freeze_shatter_damage"))), immediatesourceentity, sourceentity), (float) (amount + 3));
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(BetterToolsModMobEffects.FROZEN);
			world.levelEvent(2001, BlockPos.containing(x, y + entity.getBbHeight() / 2, z), Block.getId(Blocks.ICE.defaultBlockState()));
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.glass.break")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.glass.break")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.SNOWFLAKE, x, (y + entity.getBbHeight() / 2), z, 5, 0.6, 0.6, 0.6, 0.1);
			if (immediatesourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES).critical_hit) {
				CriticalHitParticlesProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), amount);
			}
			if (sourceentity instanceof ServerPlayer _player) {
				AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:freeze_shatter_adv"));
				if (_adv != null) {
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		}
	}
}
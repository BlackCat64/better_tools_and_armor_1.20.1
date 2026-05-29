package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModParticleTypes;

import java.util.Comparator;

public class GuardianStaffWaterPulseProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double kills = 0;
		double damage = 0;
		double vx = 0;
		double vy = 0;
		double vz = 0;
		double range = 0;
		range = 4 + 2 * itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("better_tools:ensorcellation"))));
		damage = 6 + itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("better_tools:ensorcellation"))));
		if (entity.isInWaterRainOrBubble()) {
			range = range * 1.5;
			damage = damage * 1.5;
		}
		{
			final Vec3 _center = new Vec3(x, y, z);
			for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((range * 2) / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
				if (entityiterator instanceof LivingEntity) {
					if (!(entityiterator == entity)) {
						entityiterator.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("better_tools:water_pulse_damage"))), null, entity), (float) damage);
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.NAUTILUS, (entityiterator.getX()), (entityiterator.getY() + 1), (entityiterator.getZ()), 10, 0.4, 1, 0.4, 0.025);
						if (entity instanceof ServerPlayer _player) {
							AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:guardian_staff_adv"));
							if (_adv != null) {
								AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
								if (!_ap.isDone()) {
									for (String criteria : _ap.getRemainingCriteria())
										_player.getAdvancements().award(_adv, criteria);
								}
							}
						}
						if ((entityiterator instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) <= 0) {
							kills = kills + 1;
						}
					}
				}
			}
		}
		for (int index0 = 0; index0 < (int) (4 * Math.pow(range, 2)); index0++) {
			vx = Math.random();
			vy = Math.random();
			vz = Math.random();
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (BetterToolsModParticleTypes.GUARDIAN_STAFF_BEAM.get()), (x + vx * 0.5), (y + vy * 0.5), (z + vz * 0.5), 1, vx, vy, vz, (0.03 * range));
		}
		if (kills >= 10) {
			if (entity instanceof ServerPlayer _player) {
				AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:guardian_staff_multikill_adv"));
				if (_adv != null) {
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		}
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("ambient.underwater.enter")), SoundSource.PLAYERS, 7, (float) 1.5);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("ambient.underwater.enter")), SoundSource.PLAYERS, 7, (float) 1.5, false);
			}
		}
		if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
			if (entity instanceof Player _player)
				_player.getCooldowns().addCooldown(itemstack.getItem(),
						200 - 30 * itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("better_tools:swift_cast")))));
			if (world instanceof ServerLevel _level) {
				itemstack.hurtAndBreak(1, _level, null, _stkprov -> {
				});
			}
		}
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == itemstack.getItem()) {
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
		} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == itemstack.getItem()) {
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.OFF_HAND, true);
		}
	}
}
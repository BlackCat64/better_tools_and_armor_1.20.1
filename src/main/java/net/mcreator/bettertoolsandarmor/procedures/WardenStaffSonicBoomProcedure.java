package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Comparator;

public class WardenStaffSonicBoomProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		Entity entity_to_hurt = null;
		double count = 0;
		double change_x = 0;
		double change_y = 0;
		double change_z = 0;
		double look_x = 0;
		double look_y = 0;
		double look_z = 0;
		double dist_to_wall = 0;
		count = 1;
		for (int index0 = 0; index0 < 15; index0++) {
			count = count + 1;
			look_x = entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(count)), ClipContext.Block.VISUAL, ClipContext.Fluid.ANY, entity)).getBlockPos().getX();
			look_y = entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(count)), ClipContext.Block.VISUAL, ClipContext.Fluid.ANY, entity)).getBlockPos().getY();
			look_z = entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(count)), ClipContext.Block.VISUAL, ClipContext.Fluid.ANY, entity)).getBlockPos().getZ();
			if (!world.getBlockState(BlockPos.containing(look_x, look_y, look_z)).canOcclude()) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.SONIC_BOOM, look_x, look_y, look_z, 1, 0.01, 0.01, 0.01, 0.01);
				entity_to_hurt = (Entity) world.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(new Vec3(look_x, look_y, look_z), 1.25, 1.25, 1.25), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(look_x, look_y, look_z)).findFirst().orElse(null);
				if (!(entity_to_hurt == null || entity_to_hurt == entity)) {
					entity_to_hurt.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.SONIC_BOOM), entity), 10);
					if (entity instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:warden_staff_adv"));
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
			} else {
				dist_to_wall = Math.sqrt(Math.pow(look_x - x, 2) + Math.pow(look_y - (y + 1), 2) + Math.pow(look_z - z, 2));
				change_x = (look_x - x) / dist_to_wall;
				change_y = (look_y - (y + 1)) / dist_to_wall;
				change_z = (look_z - z) / dist_to_wall;
				break;
			}
		}
		if (change_x != 0 || change_y != 0 || change_z != 0) {
			for (int index1 = 0; index1 < (int) (15 - count); index1++) {
				count = count + 1;
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.SONIC_BOOM, (x + count * change_x), (y + 1 + count * change_y), (z + count * change_z), 1, 0.01, 0.01, 0.01, 0.01);
				entity_to_hurt = (Entity) world.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(new Vec3((x + count * change_x), (y + 1 + count * change_y), (z + count * change_z)), 1.25, 1.25, 1.25), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf((x + count * change_x), (y + 1 + count * change_y), (z + count * change_z))).findFirst().orElse(null);
				if (!(entity_to_hurt == null || entity_to_hurt == entity)) {
					entity_to_hurt.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.SONIC_BOOM), entity), 10);
					if (entity instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:warden_staff_adv"));
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
					if (entity instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:shoot_through_walls_adv"));
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
			}
		}
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.warden.sonic_boom")), SoundSource.PLAYERS, (float) 0.3, (float) 1.5);
			} else {
				_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.warden.sonic_boom")), SoundSource.PLAYERS, (float) 0.3, (float) 1.5, false);
			}
		}
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
			{
				ItemStack _ist = itemstack;
				if (_ist.hurt(1, RandomSource.create(), null)) {
					_ist.shrink(1);
					_ist.setDamageValue(0);
				}
			}
			if (entity instanceof Player _player)
				_player.getCooldowns().addCooldown(itemstack.getItem(), 200);
		}
	}
}

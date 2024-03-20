package net.mcreator.bettertoolsandarmor.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.LlamaSpit;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ReflectCharmProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getSource().getDirectEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity immediatesourceentity) {
		execute(null, world, x, y, z, entity, immediatesourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity immediatesourceentity) {
		if (entity == null || immediatesourceentity == null)
			return;
		double projectile_speed = 0;
		if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(BetterToolsModItems.REFLECT_CHARM.get(), lv).isPresent() : false) {
			if ((immediatesourceentity instanceof Projectile _projEnt ? _projEnt.getDeltaMovement().length() : 0) > 0) {
				projectile_speed = Math.pow(immediatesourceentity.getDeltaMovement().x(), 2) + Math.pow(immediatesourceentity.getDeltaMovement().y(), 2) + Math.pow(immediatesourceentity.getDeltaMovement().z(), 2);
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
				if (entity instanceof ServerPlayer _player) {
					Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:reflect_projectile_adv"));
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
			if (immediatesourceentity instanceof Arrow) {
				if (!immediatesourceentity.level().isClientSide())
					immediatesourceentity.discard();
				if (world instanceof ServerLevel projectileLevel) {
					Projectile _entityToSpawn = new Object() {
						public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
							AbstractArrow entityToSpawn = new Arrow(EntityType.ARROW, level);
							entityToSpawn.setOwner(shooter);
							entityToSpawn.setBaseDamage(damage);
							entityToSpawn.setKnockback(knockback);
							entityToSpawn.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
							return entityToSpawn;
						}
					}.getArrow(projectileLevel, entity, (float) (immediatesourceentity instanceof Projectile _projEnt ? _projEnt.getDeltaMovement().length() : 0), (int) 0.75);
					_entityToSpawn.setPos(x, (y + 2), z);
					_entityToSpawn.shoot((immediatesourceentity.getDeltaMovement().x() * (-1)), (immediatesourceentity.getDeltaMovement().y() * (-1)), (immediatesourceentity.getDeltaMovement().z() * (-1)), (float) (projectile_speed / 2), 0);
					projectileLevel.addFreshEntity(_entityToSpawn);
				}
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), 1);
			} else if (immediatesourceentity instanceof LargeFireball) {
				if (!immediatesourceentity.level().isClientSide())
					immediatesourceentity.discard();
				if (world instanceof ServerLevel projectileLevel) {
					Projectile _entityToSpawn = new Object() {
						public Projectile getFireball(Level level, Entity shooter, double ax, double ay, double az) {
							AbstractHurtingProjectile entityToSpawn = new LargeFireball(EntityType.FIREBALL, level);
							entityToSpawn.setOwner(shooter);
							entityToSpawn.xPower = ax;
							entityToSpawn.yPower = ay;
							entityToSpawn.zPower = az;
							return entityToSpawn;
						}
					}.getFireball(projectileLevel, entity, ((immediatesourceentity.getDeltaMovement().x() * (-0.1)) / projectile_speed), ((immediatesourceentity.getDeltaMovement().y() * (-0.1)) / projectile_speed),
							((immediatesourceentity.getDeltaMovement().z() * (-0.1)) / projectile_speed));
					_entityToSpawn.setPos(x, (y + 1), z);
					_entityToSpawn.shoot((immediatesourceentity.getDeltaMovement().x() * (-1)), (immediatesourceentity.getDeltaMovement().y() * (-1)), (immediatesourceentity.getDeltaMovement().z() * (-1)), (float) projectile_speed, (float) 0.05);
					projectileLevel.addFreshEntity(_entityToSpawn);
				}
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.FIREBALL)), 4);
			} else if (immediatesourceentity instanceof SmallFireball) {
				if (!immediatesourceentity.level().isClientSide())
					immediatesourceentity.discard();
				if (world instanceof ServerLevel projectileLevel) {
					Projectile _entityToSpawn = new Object() {
						public Projectile getFireball(Level level, Entity shooter, double ax, double ay, double az) {
							AbstractHurtingProjectile entityToSpawn = new SmallFireball(EntityType.SMALL_FIREBALL, level);
							entityToSpawn.setOwner(shooter);
							entityToSpawn.xPower = ax;
							entityToSpawn.yPower = ay;
							entityToSpawn.zPower = az;
							return entityToSpawn;
						}
					}.getFireball(projectileLevel, entity, ((immediatesourceentity.getDeltaMovement().x() * (-0.1)) / projectile_speed), ((immediatesourceentity.getDeltaMovement().y() * (-0.1)) / projectile_speed),
							((immediatesourceentity.getDeltaMovement().z() * (-0.1)) / projectile_speed));
					_entityToSpawn.setPos(x, (y + 1), z);
					_entityToSpawn.shoot((immediatesourceentity.getDeltaMovement().x() * (-1)), (immediatesourceentity.getDeltaMovement().y() * (-1)), (immediatesourceentity.getDeltaMovement().z() * (-1)), (float) projectile_speed, (float) 0.05);
					projectileLevel.addFreshEntity(_entityToSpawn);
				}
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.FIREBALL)), 1);
			} else if (immediatesourceentity instanceof Snowball) {
				if (!immediatesourceentity.level().isClientSide())
					immediatesourceentity.discard();
				if (world instanceof ServerLevel projectileLevel) {
					Projectile _entityToSpawn = new Object() {
						public Projectile getProjectile(Level level, Entity shooter) {
							Projectile entityToSpawn = new Snowball(EntityType.SNOWBALL, level);
							entityToSpawn.setOwner(shooter);
							return entityToSpawn;
						}
					}.getProjectile(projectileLevel, entity);
					_entityToSpawn.setPos(x, (y + 1), z);
					_entityToSpawn.shoot((immediatesourceentity.getDeltaMovement().x() * (-1)), (immediatesourceentity.getDeltaMovement().y() * (-1)), (immediatesourceentity.getDeltaMovement().z() * (-1)), (float) projectile_speed, 0);
					projectileLevel.addFreshEntity(_entityToSpawn);
				}
			} else if (immediatesourceentity instanceof ThrownEgg) {
				if (!immediatesourceentity.level().isClientSide())
					immediatesourceentity.discard();
				if (world instanceof ServerLevel projectileLevel) {
					Projectile _entityToSpawn = new Object() {
						public Projectile getProjectile(Level level, Entity shooter) {
							Projectile entityToSpawn = new ThrownEgg(EntityType.EGG, level);
							entityToSpawn.setOwner(shooter);
							return entityToSpawn;
						}
					}.getProjectile(projectileLevel, entity);
					_entityToSpawn.setPos(x, (y + 1), z);
					_entityToSpawn.shoot((immediatesourceentity.getDeltaMovement().x() * (-1)), (immediatesourceentity.getDeltaMovement().y() * (-1)), (immediatesourceentity.getDeltaMovement().z() * (-1)), (float) projectile_speed, 0);
					projectileLevel.addFreshEntity(_entityToSpawn);
				}
			} else if (immediatesourceentity instanceof LlamaSpit) {
				if (!immediatesourceentity.level().isClientSide())
					immediatesourceentity.discard();
				if (world instanceof ServerLevel projectileLevel) {
					Projectile _entityToSpawn = new Object() {
						public Projectile getProjectile(Level level, Entity shooter) {
							Projectile entityToSpawn = new LlamaSpit(EntityType.LLAMA_SPIT, level);
							entityToSpawn.setOwner(shooter);
							return entityToSpawn;
						}
					}.getProjectile(projectileLevel, entity);
					_entityToSpawn.setPos(x, (y + 1), z);
					_entityToSpawn.shoot((immediatesourceentity.getDeltaMovement().x() * (-1)), (immediatesourceentity.getDeltaMovement().y() * (-1)), (immediatesourceentity.getDeltaMovement().z() * (-1)), (float) projectile_speed, 0);
					projectileLevel.addFreshEntity(_entityToSpawn);
				}
			}
		}
	}
}

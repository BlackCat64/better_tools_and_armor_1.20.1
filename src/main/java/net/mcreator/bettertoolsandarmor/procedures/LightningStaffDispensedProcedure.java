package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModEntities;
import net.mcreator.bettertoolsandarmor.entity.LightningStaffDispenserProjectileEntity;

public class LightningStaffDispensedProcedure {
	public static ItemStack execute(LevelAccessor world, double x, double y, double z, Direction direction, ItemStack itemstack, boolean success) {
		if (direction == null)
			return ItemStack.EMPTY;
		double x_offset = 0;
		double y_offset = 0;
		double z_offset = 0;
		double x_dir = 0;
		double y_dir = 0;
		double z_dir = 0;
		if (success) {
			if (world instanceof ServerLevel projectileLevel) {
				Projectile _entityToSpawn = new Object() {
					public Projectile getArrow(Level level, float damage, int knockback, byte piercing) {
						AbstractArrow entityToSpawn = new LightningStaffDispenserProjectileEntity(BetterToolsModEntities.LIGHTNING_STAFF_DISPENSER_PROJECTILE.get(), level) {
							@Override
							public byte getPierceLevel() {
								return piercing;
							}

							@Override
							protected void doKnockback(LivingEntity livingEntity, DamageSource damageSource) {
								if (knockback > 0) {
									double d1 = Math.max(0.0, 1.0 - livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
									Vec3 vec3 = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale(knockback * 0.6 * d1);
									if (vec3.lengthSqr() > 0.0) {
										livingEntity.push(vec3.x, 0.1, vec3.z);
									}
								}
							}
						};
						entityToSpawn.setBaseDamage(damage);
						entityToSpawn.setSilent(true);
						return entityToSpawn;
					}
				}.getArrow(projectileLevel, (float) 0.2, 0, (byte) 0);
				_entityToSpawn.setPos((x + 0.5 + 0.6 * direction.getStepX()), (y + 0.5 + 0.6 * direction.getStepY()), (z + 0.5 + 0.6 * direction.getStepZ()));
				_entityToSpawn.shoot((direction.getStepX()), (direction.getStepY()), (direction.getStepZ()), 1, 0);
				projectileLevel.addFreshEntity(_entityToSpawn);
			}
			if (world instanceof ServerLevel _level) {
				itemstack.hurtAndBreak(1, _level, null, _stkprov -> {
				});
			}
		}
		return itemstack;
	}
}

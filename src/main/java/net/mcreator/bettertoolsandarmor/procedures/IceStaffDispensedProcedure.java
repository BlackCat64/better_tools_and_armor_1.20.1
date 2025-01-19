package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModEntities;
import net.mcreator.bettertoolsandarmor.entity.IceStaffProjectileFromDispenserEntity;

public class IceStaffDispensedProcedure {
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
					public Projectile getArrow(Level level, float damage, int knockback) {
						AbstractArrow entityToSpawn = new IceStaffProjectileFromDispenserEntity(BetterToolsModEntities.ICE_STAFF_PROJECTILE_FROM_DISPENSER.get(), level);
						entityToSpawn.setBaseDamage(damage);
						entityToSpawn.setKnockback(knockback);
						entityToSpawn.setSilent(true);
						return entityToSpawn;
					}
				}.getArrow(projectileLevel, (float) 0.2, 0);
				_entityToSpawn.setPos((x + 0.5 + 0.6 * direction.getStepX()), (y + 0.5 + 0.6 * direction.getStepY()), (z + 0.5 + 0.6 * direction.getStepZ()));
				_entityToSpawn.shoot((direction.getStepX()), (direction.getStepY()), (direction.getStepZ()), 1, 0);
				projectileLevel.addFreshEntity(_entityToSpawn);
			}
			{
				ItemStack _ist = itemstack;
				if (_ist.hurt(1, RandomSource.create(), null)) {
					_ist.shrink(1);
					_ist.setDamageValue(0);
				}
			}
		}
		return itemstack;
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.Entity;
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
				Projectile _entityToSpawn = initArrowProjectile(new IceStaffProjectileFromDispenserEntity(BetterToolsModEntities.ICE_STAFF_PROJECTILE_FROM_DISPENSER.get(), projectileLevel), null, (float) 0.2, true, false, false,
						AbstractArrow.Pickup.DISALLOWED);
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

	private static AbstractArrow initArrowProjectile(AbstractArrow entityToSpawn, Entity shooter, float damage, boolean silent, boolean fire, boolean particles, AbstractArrow.Pickup pickup) {
		entityToSpawn.setOwner(shooter);
		entityToSpawn.setBaseDamage(damage);
		if (silent)
			entityToSpawn.setSilent(true);
		if (fire)
			entityToSpawn.igniteForSeconds(100);
		if (particles)
			entityToSpawn.setCritArrow(true);
		entityToSpawn.pickup = pickup;
		return entityToSpawn;
	}
}

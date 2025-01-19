package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModEnchantments;
import net.mcreator.bettertoolsandarmor.entity.ElectricStaffProjectileEntity;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import java.util.List;
import java.util.Comparator;

public class LightningStaffApplyEnchantmentsProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		StaffSaveDurabilityProcedureProcedure.execute(entity);
		BetterToolsMod.queueServerWork(1, () -> {
			{
				final Vec3 _center = new Vec3(
						(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4)), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, entity)).getBlockPos().getX()),
						(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4)), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, entity)).getBlockPos().getY()),
						(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4)), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, entity)).getBlockPos().getZ()));
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(6 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (!(entityiterator == entity) && entityiterator instanceof ElectricStaffProjectileEntity) {
						entityiterator.getPersistentData().putDouble("strikes", (itemstack.getEnchantmentLevel(BetterToolsModEnchantments.ENSORCELLATION.get()) + 1));
						if (IsInThunderstormProcedure.execute(world, x, y, z)) {
							entityiterator.getPersistentData().putDouble("strikes", (entityiterator.getPersistentData().getDouble("strikes") * 2));
						}
						entityiterator.getPersistentData().putDouble("radius", (2.5 + itemstack.getEnchantmentLevel(BetterToolsModEnchantments.ENSORCELLATION.get()) * 0.5));
						entityiterator.getPersistentData().putDouble("cooldown_ticks_on_hit", (200 - 30 * itemstack.getEnchantmentLevel(BetterToolsModEnchantments.SWIFT_CAST.get())));
					}
				}
			}
		});
	}
}

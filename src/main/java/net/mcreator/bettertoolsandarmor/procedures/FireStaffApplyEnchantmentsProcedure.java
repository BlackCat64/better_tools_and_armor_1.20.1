package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModEnchantments;
import net.mcreator.bettertoolsandarmor.entity.FireStaffProjectileEntity;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import java.util.List;
import java.util.Comparator;

public class FireStaffApplyEnchantmentsProcedure {
	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack) {
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
					if (!(entityiterator == entity) && entityiterator instanceof FireStaffProjectileEntity) {
						if (EnchantmentHelper.getItemEnchantmentLevel(BetterToolsModEnchantments.ENSORCELLATION.get(), itemstack) != 0) {
							entityiterator.getPersistentData().putDouble("explosion_power", (0.5 + 0.5 * itemstack.getEnchantmentLevel(BetterToolsModEnchantments.ENSORCELLATION.get())));
							if ((entityiterator.level().dimension()) == Level.NETHER) {
								entityiterator.getPersistentData().putDouble("explosion_power", (entityiterator.getPersistentData().getDouble("explosion_power") * 2));
							}
						}
						entityiterator.getPersistentData().putDouble("cooldown_ticks_on_hit", (200 - 30 * itemstack.getEnchantmentLevel(BetterToolsModEnchantments.SWIFT_CAST.get())));
					}
				}
			}
		});
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;

import java.util.Comparator;

public class IsNearestItemEntityNaturallyDroppedProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double dupe_chance = 0;
		return new Object() {
			public double getValue() {
				CompoundTag dataIndex1 = new CompoundTag();
				((Entity) world.getEntitiesOfClass(ItemEntity.class, AABB.ofSize(new Vec3((x + 0.5), (y + 0.8), (z + 0.5)), 1.25, 1.25, 1.25), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf((x + 0.5), (y + 0.8), (z + 0.5))).findFirst().orElse(null)).saveWithoutId(dataIndex1);
				return dataIndex1.getDouble("Age");
			}
		}.getValue() <= 5 && !((Entity) world.getEntitiesOfClass(ItemEntity.class, AABB.ofSize(new Vec3((x + 0.5), (y + 0.8), (z + 0.5)), 1.25, 1.25, 1.25), e -> true).stream().sorted(new Object() {
			Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
				return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
			}
		}.compareDistOf((x + 0.5), (y + 0.8), (z + 0.5))).findFirst().orElse(null)).onGround()
				&& (((Entity) world.getEntitiesOfClass(ItemEntity.class, AABB.ofSize(new Vec3((x + 0.5), (y + 0.8), (z + 0.5)), 1.25, 1.25, 1.25), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf((x + 0.5), (y + 0.8), (z + 0.5))).findFirst().orElse(null)).getDeltaMovement().x() > 0.001
						|| ((Entity) world.getEntitiesOfClass(ItemEntity.class, AABB.ofSize(new Vec3((x + 0.5), (y + 0.8), (z + 0.5)), 1.25, 1.25, 1.25), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf((x + 0.5), (y + 0.8), (z + 0.5))).findFirst().orElse(null)).getDeltaMovement().y() != 0
						|| ((Entity) world.getEntitiesOfClass(ItemEntity.class, AABB.ofSize(new Vec3((x + 0.5), (y + 0.8), (z + 0.5)), 1.25, 1.25, 1.25), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf((x + 0.5), (y + 0.8), (z + 0.5))).findFirst().orElse(null)).getDeltaMovement().z() > 0.001);
	}
}

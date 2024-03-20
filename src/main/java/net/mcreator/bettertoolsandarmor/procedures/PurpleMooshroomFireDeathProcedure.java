package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import java.util.List;
import java.util.Comparator;

public class PurpleMooshroomFireDeathProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.isOnFire()) {
			BetterToolsMod.queueServerWork(2, () -> {
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(2 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if ((entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getItem() == Items.BEEF && (entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getCount() <= 8) {
							for (int index0 = 0; index0 < (entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getCount(); index0++) {
								if (world instanceof ServerLevel _level) {
									ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.COOKED_BEEF));
									entityToSpawn.setPickUpDelay(5);
									_level.addFreshEntity(entityToSpawn);
								}
							}
							if (!entityiterator.level().isClientSide())
								entityiterator.discard();
							break;
						}
					}
				}
				StopItemsProcedureProcedure.execute(world, x, y, z);
			});
		}
	}
}

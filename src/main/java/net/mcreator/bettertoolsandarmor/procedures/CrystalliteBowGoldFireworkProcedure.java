package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.component.DataComponents;

import java.util.List;

import it.unimi.dsi.fastutil.ints.IntList;

public class CrystalliteBowGoldFireworkProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity sourceentity) {
		if (sourceentity == null)
			return;
		ItemStack firework_item = new ItemStack(Items.FIREWORK_ROCKET);
		FireworkExplosion explosion = new FireworkExplosion(FireworkExplosion.Shape.SMALL_BALL, IntList.of(16645946, 13426154), IntList.of(), false, false);
		Fireworks fireworks = new Fireworks(1, List.of(explosion));
		firework_item.set(DataComponents.FIREWORKS, fireworks);

		if (world instanceof ServerLevel serverLevel) {
			FireworkRocketEntity firework = new FireworkRocketEntity(serverLevel, x, y, z, firework_item);
			CompoundTag tag = new CompoundTag();
			firework.saveWithoutId(tag);
			tag.putInt("LifeTime", 0);
			tag.putInt("Life", 0);
			firework.load(tag);
			firework.setOwner(sourceentity);
			serverLevel.addFreshEntity(firework);
		}
	}
}

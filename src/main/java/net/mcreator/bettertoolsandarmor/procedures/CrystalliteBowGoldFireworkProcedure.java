package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.CompoundTag;

public class CrystalliteBowGoldFireworkProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity sourceentity) {
		if (sourceentity == null)
			return;
		ItemStack firework_item = ItemStack.EMPTY;
		if (false) {
			if (!world.isClientSide() && world.getServer() != null)
				world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((x + "" + y + z + sourceentity)), false);
		}
		firework_item = new ItemStack(Items.FIREWORK_ROCKET);
		CompoundTag explosion = new CompoundTag();
		explosion.putByte("Type", (byte) 0);
		explosion.put("Colors", new IntArrayTag(new int[]{16645946, 13426154}));
		explosion.putBoolean("Flicker", false);
		explosion.putBoolean("Trail", false);
		ListTag explosions = new ListTag();
		explosions.add(explosion);
		CompoundTag fireworksTag = new CompoundTag();
		fireworksTag.putByte("Flight", (byte) 1);
		fireworksTag.put("Explosions", explosions);
		firework_item.getOrCreateTag().put("Fireworks", fireworksTag);
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

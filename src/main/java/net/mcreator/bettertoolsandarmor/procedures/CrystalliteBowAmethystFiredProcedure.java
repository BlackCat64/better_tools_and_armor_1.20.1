package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class CrystalliteBowAmethystFiredProcedure {
	public static void execute(LevelAccessor world, Entity arrow, Entity player, double homing_radius) {
		if (arrow == null || player == null)
			return;
		Entity armor_stand = null;
		if (false) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = EntityType.ARMOR_STAND.spawn(_level, BlockPos.containing(arrow.getX(), arrow.getY(), arrow.getZ()), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		}
		if (world instanceof ServerLevel _level) {
			armor_stand = new ArmorStand(_level, arrow.getX(), arrow.getY(), arrow.getZ());
		}
		if (!(armor_stand == null)) {
			armor_stand.setInvisible(true);
			armor_stand.setNoGravity(true);
			SetEntityLogicDataProcedure.execute(armor_stand, true, "Marker");
			SetEntityLogicDataProcedure.execute(armor_stand, true, "Invulnerable");
			SetEntityNumberDataProcedure.execute(armor_stand, 4144959, "DisabledSlots");
			armor_stand.getPersistentData().putBoolean("crystallite_bow_amethyst", true);
			armor_stand.getPersistentData().putString("arrow", (arrow.getStringUUID()));
			armor_stand.getPersistentData().putString("player", (player.getStringUUID()));
			armor_stand.getPersistentData().putDouble("homing_radius", homing_radius);
			((ServerLevel) world).addFreshEntity(armor_stand);
		}
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

public class CrystalliteBowGoldFiredProcedure {
	public static void execute(LevelAccessor world, Entity arrow, Entity player) {
		if (arrow == null || player == null)
			return;
		Entity armor_stand = null;
		armor_stand = world instanceof Level _level ? new ArmorStand(EntityType.ARMOR_STAND, _level) : null;
		if (!(armor_stand == null)) {
			{
				Entity _ent = armor_stand;
				_ent.teleportTo((arrow.getX()), (arrow.getY()), (arrow.getZ()));
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport((arrow.getX()), (arrow.getY()), (arrow.getZ()), _ent.getYRot(), _ent.getXRot());
			}
			armor_stand.setInvisible(true);
			armor_stand.setNoGravity(true);
			SetEntityLogicDataProcedure.execute(armor_stand, true, "Marker");
			SetEntityLogicDataProcedure.execute(armor_stand, true, "Invulnerable");
			SetEntityNumberDataProcedure.execute(armor_stand, 4144959, "DisabledSlots");
			armor_stand.getPersistentData().putBoolean("crystallite_bow_gold", true);
			armor_stand.getPersistentData().putString("arrow", (arrow.getStringUUID()));
			armor_stand.getPersistentData().putString("player", (player.getStringUUID()));
			if (world instanceof Level _level) {
				_level.addFreshEntity(armor_stand);
			}
		}
	}
}
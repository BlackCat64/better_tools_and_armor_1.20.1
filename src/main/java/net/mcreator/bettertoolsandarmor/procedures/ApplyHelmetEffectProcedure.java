package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class ApplyHelmetEffectProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player)
			_player.giveExperienceLevels(1);
	}
}

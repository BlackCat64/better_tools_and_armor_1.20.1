package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class FrozenEffectExpiresProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		DeleteEntityIceBlockDisplayProcedure.execute(entity);
		if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
			BetterToolsMod.queueServerWork(5, () -> {
				entity.setTicksFrozen(55);
			});
		}
	}
}

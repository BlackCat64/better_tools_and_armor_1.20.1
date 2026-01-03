package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import java.util.UUID;

public class FrozenEffectExpiresProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).removeModifier(UUID.fromString("06be9690-876a-468f-9d10-25eb7c432664"));
		DeleteEntityIceBlockDisplayProcedure.execute(entity);
		if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
			BetterToolsMod.queueServerWork(5, () -> {
				entity.setTicksFrozen(55);
			});
		}
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class FrozenEffectExpiresProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity) {
			_entity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(ResourceLocation.parse("better_tools:frozen_effect"));
		}
		DeleteEntityIceBlockDisplayProcedure.execute(world, entity);
		if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
			BetterToolsMod.queueServerWork(5, () -> {
				entity.setTicksFrozen(55);
			});
		}
	}
}
package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;

public class DoubleJumpProcedureProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.onGround() && entity.getDeltaMovement().y() >= -0.5) {
			{
				double _setval = (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(BetterToolsModMobEffects.DOUBLE_JUMP.get()) ? _livEnt.getEffect(BetterToolsModMobEffects.DOUBLE_JUMP.get()).getAmplifier() : 0) + 1;
				entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.extra_jumps = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if (entity instanceof ServerPlayer _player) {
			Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:double_jump_adv"));
			AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
			if (!_ap.isDone()) {
				for (String criteria : _ap.getRemainingCriteria())
					_player.getAdvancements().award(_adv, criteria);
			}
		}
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import javax.annotation.Nullable;

@EventBusSubscriber
public class ArrowDamageAdvProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingIncomingDamageEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getSource().getDirectEntity(), event.getSource().getEntity(), event.getAmount());
		}
	}

	public static void execute(Entity immediatesourceentity, Entity sourceentity, double amount) {
		execute(null, immediatesourceentity, sourceentity, amount);
	}

	private static void execute(@Nullable Event event, Entity immediatesourceentity, Entity sourceentity, double amount) {
		if (immediatesourceentity == null || sourceentity == null)
			return;
		if (sourceentity instanceof Player && immediatesourceentity instanceof Arrow) {
			if (amount > 35) {
				if (sourceentity instanceof ServerPlayer _player) {
					AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:high_arrow_damage_adv"));
					if (_adv != null) {
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
			}
		}
	}
}
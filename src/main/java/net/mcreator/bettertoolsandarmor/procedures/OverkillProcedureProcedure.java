package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import javax.annotation.Nullable;

@EventBusSubscriber
public class OverkillProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingIncomingDamageEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getSource(), event.getSource().getDirectEntity(), event.getAmount());
		}
	}

	public static void execute(DamageSource damagesource, Entity immediatesourceentity, double amount) {
		execute(null, damagesource, immediatesourceentity, amount);
	}

	private static void execute(@Nullable Event event, DamageSource damagesource, Entity immediatesourceentity, double amount) {
		if (damagesource == null || immediatesourceentity == null)
			return;
		if (immediatesourceentity instanceof Player && damagesource.is(TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("better_tools:is_melee_attack"))) && amount > 30) {
			if (immediatesourceentity instanceof ServerPlayer _player) {
				AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:overkill_adv"));
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
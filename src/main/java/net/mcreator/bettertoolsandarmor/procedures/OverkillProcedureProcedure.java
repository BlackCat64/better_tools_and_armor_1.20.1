package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class OverkillProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getSource(), event.getSource().getDirectEntity(), event.getAmount());
		}
	}

	public static void execute(DamageSource damagesource, Entity immediatesourceentity, double amount) {
		execute(null, damagesource, immediatesourceentity, amount);
	}

	private static void execute(@Nullable Event event, DamageSource damagesource, Entity immediatesourceentity, double amount) {
		if (damagesource == null || immediatesourceentity == null)
			return;
		if (immediatesourceentity instanceof Player && damagesource.is(TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("better_tools:is_melee_attack"))) && amount > 30) {
			if (immediatesourceentity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:overkill_adv"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					for (String criteria : _ap.getRemainingCriteria())
						_player.getAdvancements().award(_adv, criteria);
				}
			}
		}
	}
}

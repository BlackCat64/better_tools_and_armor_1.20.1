package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteSwordSkyCritDamageProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getSource(), event.getEntity(), event.getSource().getDirectEntity(), event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, DamageSource damagesource, Entity entity, Entity immediatesourceentity, double amount) {
		execute(null, world, damagesource, entity, immediatesourceentity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, DamageSource damagesource, Entity entity, Entity immediatesourceentity, double amount) {
		if (damagesource == null || entity == null || immediatesourceentity == null)
			return;
		double distance = 0;
		if ((immediatesourceentity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).critical_hit
				&& !damagesource.is(TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("better_tools:damage_overrides")))) {
			if (immediatesourceentity instanceof Player && ((LivingEntity) immediatesourceentity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get()).getValue() != 1.5) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				} else if (event != null && event.hasResult()) {
					event.setResult(Event.Result.DENY);
				}
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("better_tools:override_weapon_damage"))),
						immediatesourceentity, immediatesourceentity), (float) (amount * ((LivingEntity) immediatesourceentity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get()).getValue()));
				if (((LivingEntity) immediatesourceentity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get()).getValue() >= 2.5) {
					if (immediatesourceentity instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:high_crit_multiplier_adv"));
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
				CriticalHitParticlesProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), amount * ((LivingEntity) immediatesourceentity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get()).getValue());
			}
		}
	}
}

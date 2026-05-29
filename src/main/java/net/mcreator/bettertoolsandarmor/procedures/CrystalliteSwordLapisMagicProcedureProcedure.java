package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteSwordLapisMagicProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingIncomingDamageEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getSource(), event.getEntity(), event.getSource().getDirectEntity(), event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, DamageSource damagesource, Entity entity, Entity immediatesourceentity, double amount) {
		execute(null, world, damagesource, entity, immediatesourceentity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, DamageSource damagesource, Entity entity, Entity immediatesourceentity, double amount) {
		if (damagesource == null || entity == null || immediatesourceentity == null)
			return;
		double damage = 0;
		if ((immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:magic_damage_tools")))
				&& !damagesource.is(TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("better_tools:damage_overrides")))) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			damage = amount;
			if (immediatesourceentity instanceof LivingEntity _livingEntity3 && _livingEntity3.getAttributes().hasAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER)
					&& immediatesourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES).critical_hit) {
				if ((immediatesourceentity instanceof LivingEntity _livingEntity4 && _livingEntity4.getAttributes().hasAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER)
						? _livingEntity4.getAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER).getValue()
						: 0) != 1.5) {
					damage = damage * (immediatesourceentity instanceof LivingEntity _livingEntity5 && _livingEntity5.getAttributes().hasAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER)
							? _livingEntity5.getAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER).getValue()
							: 0);
				}
				CriticalHitParticlesProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), amount);
			}
			entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("better_tools:magic_weapon_damage"))), immediatesourceentity, immediatesourceentity), (float) damage);
			if (!(immediatesourceentity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
				if (world instanceof ServerLevel _level) {
					(immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).hurtAndBreak(1, _level, null, _stkprov -> {
					});
				}
			}
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getArmorValue() : 0) > 0) {
				if (immediatesourceentity instanceof ServerPlayer _player) {
					AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:deal_dmg_through_armor_adv"));
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
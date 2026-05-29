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
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteSwordDiamondProcedureProcedure {
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
		double boost = 0;
		if ((immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:precision_weapons")))
				&& !damagesource.is(TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("better_tools:damage_overrides")))
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) == (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			damage = amount;
			boost = (immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_axes"))) ? 2 : 2.5;
			if (entity instanceof LivingEntity _livingEntity7 && _livingEntity7.getAttributes().hasAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER) && immediatesourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES).critical_hit) {
				if ((entity instanceof LivingEntity _livingEntity8 && _livingEntity8.getAttributes().hasAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER)
						? _livingEntity8.getAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER).getValue()
						: 0) != 1.5) {
					damage = (damage + boost) * (entity instanceof LivingEntity _livingEntity9 && _livingEntity9.getAttributes().hasAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER)
							? _livingEntity9.getAttribute(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER).getValue()
							: 0);
				} else {
					damage = damage + 1.5 * boost;
				}
			} else {
				damage = damage + boost;
			}
			entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("better_tools:override_weapon_damage"))), immediatesourceentity, immediatesourceentity), (float) damage);
			if (!(immediatesourceentity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
				if (world instanceof ServerLevel _level) {
					(immediatesourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).hurtAndBreak(1, _level, null, _stkprov -> {
					});
				}
			}
			if (immediatesourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES).critical_hit) {
				CriticalHitParticlesProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), damage);
			}
		}
	}
}
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.procedures.SwiftSwimExpiresProcedure;
import net.mcreator.bettertoolsandarmor.procedures.PitfallEffectExpiresProcedure;
import net.mcreator.bettertoolsandarmor.procedures.FrozenEffectExpiresProcedure;
import net.mcreator.bettertoolsandarmor.procedures.CriticalHitEffectExpiresProcedure;
import net.mcreator.bettertoolsandarmor.potion.SwiftSwimMobEffect;
import net.mcreator.bettertoolsandarmor.potion.PitfallMobEffect;
import net.mcreator.bettertoolsandarmor.potion.OreVisionMobEffect;
import net.mcreator.bettertoolsandarmor.potion.KarmaPotionMobEffect;
import net.mcreator.bettertoolsandarmor.potion.FrozenMobEffect;
import net.mcreator.bettertoolsandarmor.potion.DoubleJumpMobEffect;
import net.mcreator.bettertoolsandarmor.potion.CriticalityMobEffect;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

@EventBusSubscriber
public class BetterToolsModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, BetterToolsMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> KARMA_POTION = REGISTRY.register("karma_potion", () -> new KarmaPotionMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> DOUBLE_JUMP = REGISTRY.register("double_jump", () -> new DoubleJumpMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> SWIFT_SWIM = REGISTRY.register("swift_swim", () -> new SwiftSwimMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> FROZEN = REGISTRY.register("frozen", () -> new FrozenMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> CRITICALITY = REGISTRY.register("criticality", () -> new CriticalityMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> PITFALL = REGISTRY.register("pitfall", () -> new PitfallMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> ORE_VISION = REGISTRY.register("ore_vision", () -> new OreVisionMobEffect());

	@SubscribeEvent
	public static void onEffectRemoved(MobEffectEvent.Remove event) {
		MobEffectInstance effectInstance = event.getEffectInstance();
		if (effectInstance != null) {
			expireEffects(event.getEntity(), effectInstance);
		}
	}

	@SubscribeEvent
	public static void onEffectExpired(MobEffectEvent.Expired event) {
		MobEffectInstance effectInstance = event.getEffectInstance();
		if (effectInstance != null) {
			expireEffects(event.getEntity(), effectInstance);
		}
	}

	private static void expireEffects(Entity entity, MobEffectInstance effectInstance) {
		if (effectInstance.getEffect().is(SWIFT_SWIM)) {
			SwiftSwimExpiresProcedure.execute();
		} else if (effectInstance.getEffect().is(FROZEN)) {
			FrozenEffectExpiresProcedure.execute(entity.level(), entity);
		} else if (effectInstance.getEffect().is(CRITICALITY)) {
			CriticalHitEffectExpiresProcedure.execute();
		} else if (effectInstance.getEffect().is(PITFALL)) {
			PitfallEffectExpiresProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
		}
	}
}
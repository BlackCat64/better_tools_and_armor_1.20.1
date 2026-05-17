
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class BetterToolsModPotions {
	public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(Registries.POTION, BetterToolsMod.MODID);
	public static final DeferredHolder<Potion, Potion> KARMA_POTION = REGISTRY.register("karma_potion", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.KARMA_POTION, 3600, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> DOUBLE_JUMP_POTION = REGISTRY.register("double_jump_potion", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.DOUBLE_JUMP, 3600, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> KARMA_POTION_2 = REGISTRY.register("karma_potion_2", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.KARMA_POTION, 1800, 1, false, true)));
	public static final DeferredHolder<Potion, Potion> KARMA_POTION_LONG = REGISTRY.register("karma_potion_long", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.KARMA_POTION, 9600, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> DOUBLE_JUMP_POTION_LONG = REGISTRY.register("double_jump_potion_long", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.DOUBLE_JUMP, 9600, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> DOUBLE_JUMP_POTION_2 = REGISTRY.register("double_jump_potion_2", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.DOUBLE_JUMP, 1800, 1, false, true)));
	public static final DeferredHolder<Potion, Potion> LUCK_POTION_2 = REGISTRY.register("luck_potion_2", () -> new Potion(new MobEffectInstance(MobEffects.LUCK, 3000, 1, false, true)));
	public static final DeferredHolder<Potion, Potion> SWIFT_SWIM_POTION = REGISTRY.register("swift_swim_potion", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.SWIFT_SWIM, 3600, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> SWIFT_SWIM_POTION_LONG = REGISTRY.register("swift_swim_potion_long", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.SWIFT_SWIM, 9600, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> SWIFT_SWIM_POTION_2 = REGISTRY.register("swift_swim_potion_2", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.SWIFT_SWIM, 1800, 1, false, true)));
	public static final DeferredHolder<Potion, Potion> CRITICALITY_POTION = REGISTRY.register("criticality_potion", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.CRITICALITY, 3600, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> CRITICALITY_POTION_2 = REGISTRY.register("criticality_potion_2", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.CRITICALITY, 1800, 1, false, true)));
	public static final DeferredHolder<Potion, Potion> CRITICALITY_POTION_LONG = REGISTRY.register("criticality_potion_long", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.CRITICALITY, 9600, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> POTION_OF_DECAY = REGISTRY.register("potion_of_decay", () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 800, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> POTION_OF_DECAY_2 = REGISTRY.register("potion_of_decay_2", () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 480, 1, false, true)));
	public static final DeferredHolder<Potion, Potion> POTION_OF_DECAY_LONG = REGISTRY.register("potion_of_decay_long", () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 1800, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> ORE_VISION_POTION = REGISTRY.register("ore_vision_potion", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.ORE_VISION, 3600, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> ORE_VISION_POTION_LONG = REGISTRY.register("ore_vision_potion_long", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.ORE_VISION, 9600, 0, false, true)));
}

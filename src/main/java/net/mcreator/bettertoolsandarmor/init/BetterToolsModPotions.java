
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class BetterToolsModPotions {
	public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(ForgeRegistries.POTIONS, BetterToolsMod.MODID);
	public static final RegistryObject<Potion> KARMA_POTION = REGISTRY.register("karma_potion", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.KARMA_POTION.get(), 3600, 0, false, true)));
	public static final RegistryObject<Potion> DOUBLE_JUMP_POTION = REGISTRY.register("double_jump_potion", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.DOUBLE_JUMP.get(), 3600, 0, false, true)));
	public static final RegistryObject<Potion> KARMA_POTION_2 = REGISTRY.register("karma_potion_2", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.KARMA_POTION.get(), 1800, 1, false, true)));
	public static final RegistryObject<Potion> KARMA_POTION_LONG = REGISTRY.register("karma_potion_long", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.KARMA_POTION.get(), 9600, 0, false, true)));
	public static final RegistryObject<Potion> DOUBLE_JUMP_POTION_LONG = REGISTRY.register("double_jump_potion_long", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.DOUBLE_JUMP.get(), 9600, 0, false, true)));
	public static final RegistryObject<Potion> DOUBLE_JUMP_POTION_2 = REGISTRY.register("double_jump_potion_2", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.DOUBLE_JUMP.get(), 1800, 1, false, true)));
	public static final RegistryObject<Potion> LUCK_POTION_2 = REGISTRY.register("luck_potion_2", () -> new Potion(new MobEffectInstance(MobEffects.LUCK, 3000, 1, false, true)));
	public static final RegistryObject<Potion> SWIFT_SWIM_POTION = REGISTRY.register("swift_swim_potion", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.SWIFT_SWIM.get(), 3600, 0, false, true)));
	public static final RegistryObject<Potion> SWIFT_SWIM_POTION_LONG = REGISTRY.register("swift_swim_potion_long", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.SWIFT_SWIM.get(), 9600, 0, false, true)));
	public static final RegistryObject<Potion> SWIFT_SWIM_POTION_2 = REGISTRY.register("swift_swim_potion_2", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.SWIFT_SWIM.get(), 1800, 1, false, true)));
	public static final RegistryObject<Potion> CRITICALITY_POTION = REGISTRY.register("criticality_potion", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.CRITICALITY.get(), 3600, 0, false, true)));
	public static final RegistryObject<Potion> CRITICALITY_POTION_2 = REGISTRY.register("criticality_potion_2", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.CRITICALITY.get(), 1800, 1, false, true)));
	public static final RegistryObject<Potion> CRITICALITY_POTION_LONG = REGISTRY.register("criticality_potion_long", () -> new Potion(new MobEffectInstance(BetterToolsModMobEffects.CRITICALITY.get(), 9600, 0, false, true)));
	public static final RegistryObject<Potion> POTION_OF_DECAY = REGISTRY.register("potion_of_decay", () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 800, 0, false, true)));
	public static final RegistryObject<Potion> POTION_OF_DECAY_2 = REGISTRY.register("potion_of_decay_2", () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 480, 1, false, true)));
	public static final RegistryObject<Potion> POTION_OF_DECAY_LONG = REGISTRY.register("potion_of_decay_long", () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 1800, 0, false, true)));
}


/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.mcreator.bettertoolsandarmor.potion.SwiftSwimMobEffect;
import net.mcreator.bettertoolsandarmor.potion.KarmaPotionMobEffect;
import net.mcreator.bettertoolsandarmor.potion.FrozenMobEffect;
import net.mcreator.bettertoolsandarmor.potion.DoubleJumpMobEffect;
import net.mcreator.bettertoolsandarmor.potion.CriticalityMobEffect;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class BetterToolsModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BetterToolsMod.MODID);
	public static final RegistryObject<MobEffect> KARMA_POTION = REGISTRY.register("karma_potion", () -> new KarmaPotionMobEffect());
	public static final RegistryObject<MobEffect> DOUBLE_JUMP = REGISTRY.register("double_jump", () -> new DoubleJumpMobEffect());
	public static final RegistryObject<MobEffect> SWIFT_SWIM = REGISTRY.register("swift_swim", () -> new SwiftSwimMobEffect());
	public static final RegistryObject<MobEffect> FROZEN = REGISTRY.register("frozen", () -> new FrozenMobEffect());
	public static final RegistryObject<MobEffect> CRITICALITY = REGISTRY.register("criticality", () -> new CriticalityMobEffect());
}

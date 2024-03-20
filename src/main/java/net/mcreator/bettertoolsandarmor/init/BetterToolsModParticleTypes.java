
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class BetterToolsModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BetterToolsMod.MODID);
	public static final RegistryObject<SimpleParticleType> CRYSTALLITE_SPARKLE = REGISTRY.register("crystallite_sparkle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> ICE_PARTICLE = REGISTRY.register("ice_particle", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> ORE_LOCATION_PARTICLE = REGISTRY.register("ore_location_particle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> GUARDIAN_STAFF_BEAM = REGISTRY.register("guardian_staff_beam", () -> new SimpleParticleType(true));
}

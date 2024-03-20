
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class BetterToolsModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BetterToolsMod.MODID);
	public static final RegistryObject<SoundEvent> GLIDE = REGISTRY.register("glide", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("better_tools", "glide")));
	public static final RegistryObject<SoundEvent> DOWN_UNDER = REGISTRY.register("down_under", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("better_tools", "down_under")));
	public static final RegistryObject<SoundEvent> BLOCK_DANCE = REGISTRY.register("block_dance", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("better_tools", "block_dance")));
	public static final RegistryObject<SoundEvent> MASTER_BUILDERS = REGISTRY.register("master_builders", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("better_tools", "master_builders")));
	public static final RegistryObject<SoundEvent> MOGUS_DRIP = REGISTRY.register("mogus_drip", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("better_tools", "mogus_drip")));
	public static final RegistryObject<SoundEvent> RICK_ROLL = REGISTRY.register("rick_roll", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("better_tools", "rick_roll")));
	public static final RegistryObject<SoundEvent> SHRUNK_MUSIC = REGISTRY.register("shrunk_music", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("better_tools", "shrunk_music")));
	public static final RegistryObject<SoundEvent> CRYSTALLITE_BREAK = REGISTRY.register("crystallite_break", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("better_tools", "crystallite_break")));
	public static final RegistryObject<SoundEvent> CRYSTALLITE_PLACE = REGISTRY.register("crystallite_place", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("better_tools", "crystallite_place")));
	public static final RegistryObject<SoundEvent> CRYSTALLITE_STEP = REGISTRY.register("crystallite_step", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("better_tools", "crystallite_step")));
	public static final RegistryObject<SoundEvent> CRYSTALLITE_SHIMMER = REGISTRY.register("crystallite_shimmer", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("better_tools", "crystallite_shimmer")));
}

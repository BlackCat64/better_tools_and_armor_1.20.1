package net.mcreator.bettertoolsandarmor.potion;

import net.neoforged.neoforge.common.NeoForgeMod;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class SwiftSwimMobEffect extends MobEffect {
	public SwiftSwimMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -9263976);
		this.addAttributeModifier(NeoForgeMod.SWIM_SPEED, ResourceLocation.fromNamespaceAndPath(BetterToolsMod.MODID, "effect.swift_swim_0"), 0.5, AttributeModifier.Operation.ADD_VALUE);
	}
}
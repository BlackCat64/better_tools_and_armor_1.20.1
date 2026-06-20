package net.mcreator.bettertoolsandarmor.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class DoubleJumpMobEffect extends MobEffect {
	public DoubleJumpMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -3355444);
		this.addAttributeModifier(Attributes.SAFE_FALL_DISTANCE, ResourceLocation.fromNamespaceAndPath(BetterToolsMod.MODID, "effect.double_jump_0"), 1, AttributeModifier.Operation.ADD_VALUE);
	}
}
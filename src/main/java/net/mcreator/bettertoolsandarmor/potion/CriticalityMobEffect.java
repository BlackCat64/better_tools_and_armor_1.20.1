package net.mcreator.bettertoolsandarmor.potion;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class CriticalityMobEffect extends MobEffect {
	public CriticalityMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -3975635);
		this.addAttributeModifier(BetterToolsModAttributes.CRITICAL_HIT_MULTIPLIER, ResourceLocation.fromNamespaceAndPath(BetterToolsMod.MODID, "effect.criticality_0"), 0.25, AttributeModifier.Operation.ADD_VALUE);
	}
}
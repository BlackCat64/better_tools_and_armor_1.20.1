package net.mcreator.bettertoolsandarmor.mixin;

import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.core.Holder;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModBiomes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;

@Mixin(NoiseGeneratorSettings.class)
public class NoiseGeneratorSettingsMixin implements BetterToolsModBiomes.BetterToolsModNoiseGeneratorSettings {
	@Unique
	private Holder<DimensionType> better_tools_dimensionTypeReference;

	@WrapMethod(method = "surfaceRule")
	public SurfaceRules.RuleSource surfaceRule(Operation<SurfaceRules.RuleSource> original) {
		SurfaceRules.RuleSource retval = original.call();
		if (this.better_tools_dimensionTypeReference != null) {
			retval = BetterToolsModBiomes.adaptSurfaceRule(retval, this.better_tools_dimensionTypeReference);
		}
		return retval;
	}

	@Override
	public void setbetter_toolsDimensionTypeReference(Holder<DimensionType> dimensionType) {
		this.better_tools_dimensionTypeReference = dimensionType;
	}
}
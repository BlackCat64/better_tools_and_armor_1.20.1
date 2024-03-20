
package net.mcreator.bettertoolsandarmor.world.features;

import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.WorldGenLevel;

import net.mcreator.bettertoolsandarmor.procedures.DeepslateRubyOreGenerationConditionProcedure;

public class DeepslateRubyOreFeatureFeature extends OreFeature {
	public DeepslateRubyOreFeatureFeature() {
		super(OreConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<OreConfiguration> context) {
		WorldGenLevel world = context.level();
		int x = context.origin().getX();
		int y = context.origin().getY();
		int z = context.origin().getZ();
		if (!DeepslateRubyOreGenerationConditionProcedure.execute(y))
			return false;
		return super.place(context);
	}
}

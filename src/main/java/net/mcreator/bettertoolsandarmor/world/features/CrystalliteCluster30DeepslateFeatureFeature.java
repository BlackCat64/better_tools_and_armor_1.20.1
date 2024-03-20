
package net.mcreator.bettertoolsandarmor.world.features;

import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.WorldGenLevel;

import net.mcreator.bettertoolsandarmor.world.features.configurations.StructureFeatureConfiguration;
import net.mcreator.bettertoolsandarmor.procedures.CrystalliteClusterDeepslateGenerationConditionProcedure;

public class CrystalliteCluster30DeepslateFeatureFeature extends StructureFeature {
	public CrystalliteCluster30DeepslateFeatureFeature() {
		super(StructureFeatureConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<StructureFeatureConfiguration> context) {
		WorldGenLevel world = context.level();
		int x = context.origin().getX();
		int y = context.origin().getY();
		int z = context.origin().getZ();
		if (!CrystalliteClusterDeepslateGenerationConditionProcedure.execute(world, x, y, z))
			return false;
		return super.place(context);
	}
}

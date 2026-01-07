
package net.mcreator.bettertoolsandarmor.world.features;

public class CrystalliteCluster34FeatureFeature extends StructureFeature {
	public CrystalliteCluster34FeatureFeature() {
		super(StructureFeatureConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<StructureFeatureConfiguration> context) {
		WorldGenLevel world = context.level();
		int x = context.origin().getX();
		int y = context.origin().getY();
		int z = context.origin().getZ();
		if (!CrystalliteClusterGenerationConditionProcedure.execute(world, x, y, z))
			return false;
		return super.place(context);
	}
}
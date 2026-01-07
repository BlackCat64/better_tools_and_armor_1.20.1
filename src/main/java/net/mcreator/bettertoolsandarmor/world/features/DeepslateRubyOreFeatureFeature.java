
package net.mcreator.bettertoolsandarmor.world.features;

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
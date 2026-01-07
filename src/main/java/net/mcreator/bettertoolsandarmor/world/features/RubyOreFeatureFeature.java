
package net.mcreator.bettertoolsandarmor.world.features;

public class RubyOreFeatureFeature extends OreFeature {
	public RubyOreFeatureFeature() {
		super(OreConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<OreConfiguration> context) {
		WorldGenLevel world = context.level();
		int x = context.origin().getX();
		int y = context.origin().getY();
		int z = context.origin().getZ();
		if (!RubyOreGenerationConditionProcedure.execute(y))
			return false;
		return super.place(context);
	}
}
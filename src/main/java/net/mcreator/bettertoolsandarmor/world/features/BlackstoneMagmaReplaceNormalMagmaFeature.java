
package net.mcreator.bettertoolsandarmor.world.features;

public class BlackstoneMagmaReplaceNormalMagmaFeature extends ReplaceBlobsFeature {
	public BlackstoneMagmaReplaceNormalMagmaFeature() {
		super(ReplaceSphereConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<ReplaceSphereConfiguration> context) {
		WorldGenLevel world = context.level();
		int x = context.origin().getX();
		int y = context.origin().getY();
		int z = context.origin().getZ();
		if (!NearbyLavaCheckProcedure.execute(world, x, y, z))
			return false;
		return super.place(context);
	}
}
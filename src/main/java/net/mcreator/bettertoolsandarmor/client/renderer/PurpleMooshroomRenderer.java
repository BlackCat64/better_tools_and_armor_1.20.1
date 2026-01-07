
package net.mcreator.bettertoolsandarmor.client.renderer;

public class PurpleMooshroomRenderer extends MobRenderer<PurpleMooshroomEntity, CowModel<PurpleMooshroomEntity>> {

	public PurpleMooshroomRenderer(EntityRendererProvider.Context context) {
		super(context, new CowModel(context.bakeLayer(ModelLayers.COW)), 0.5f);

	}

	@Override
	public ResourceLocation getTextureLocation(PurpleMooshroomEntity entity) {
		return new ResourceLocation("better_tools:textures/entities/purple_mooshroom.png");
	}

}

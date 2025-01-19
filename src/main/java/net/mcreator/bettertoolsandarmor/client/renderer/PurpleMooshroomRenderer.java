
package net.mcreator.bettertoolsandarmor.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.CowModel;

import net.mcreator.bettertoolsandarmor.entity.PurpleMooshroomEntity;

public class PurpleMooshroomRenderer extends MobRenderer<PurpleMooshroomEntity, CowModel<PurpleMooshroomEntity>> {
	public PurpleMooshroomRenderer(EntityRendererProvider.Context context) {
		super(context, new CowModel(context.bakeLayer(ModelLayers.COW)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(PurpleMooshroomEntity entity) {
		return new ResourceLocation("better_tools:textures/entities/purple_mooshroom.png");
	}
}

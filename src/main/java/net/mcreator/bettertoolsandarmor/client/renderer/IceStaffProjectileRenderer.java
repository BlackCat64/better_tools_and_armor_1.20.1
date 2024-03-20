package net.mcreator.bettertoolsandarmor.client.renderer;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.bettertoolsandarmor.entity.IceStaffProjectileEntity;
import net.mcreator.bettertoolsandarmor.client.model.Modelice_staff_projectile;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class IceStaffProjectileRenderer extends EntityRenderer<IceStaffProjectileEntity> {
	private static final ResourceLocation texture = new ResourceLocation("better_tools:textures/entities/ice_effect.png");
	private final Modelice_staff_projectile model;

	public IceStaffProjectileRenderer(EntityRendererProvider.Context context) {
		super(context);
		model = new Modelice_staff_projectile(context.bakeLayer(Modelice_staff_projectile.LAYER_LOCATION));
	}

	@Override
	public void render(IceStaffProjectileEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
		VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90));
		poseStack.mulPose(Axis.ZP.rotationDegrees(90 + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
		model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
		poseStack.popPose();
		super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(IceStaffProjectileEntity entity) {
		return texture;
	}
}

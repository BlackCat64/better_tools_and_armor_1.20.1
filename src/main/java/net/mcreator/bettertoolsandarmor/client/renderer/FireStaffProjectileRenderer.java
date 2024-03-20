package net.mcreator.bettertoolsandarmor.client.renderer;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.bettertoolsandarmor.entity.FireStaffProjectileEntity;
import net.mcreator.bettertoolsandarmor.client.model.Modelfire_staff_projectile;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class FireStaffProjectileRenderer extends EntityRenderer<FireStaffProjectileEntity> {
	private static final ResourceLocation texture = new ResourceLocation("better_tools:textures/entities/flame_effect.png");
	private final Modelfire_staff_projectile model;

	public FireStaffProjectileRenderer(EntityRendererProvider.Context context) {
		super(context);
		model = new Modelfire_staff_projectile(context.bakeLayer(Modelfire_staff_projectile.LAYER_LOCATION));
	}

	@Override
	public void render(FireStaffProjectileEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
		VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90));
		poseStack.mulPose(Axis.ZP.rotationDegrees(90 + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
		model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
		poseStack.popPose();
		super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(FireStaffProjectileEntity entity) {
		return texture;
	}
}

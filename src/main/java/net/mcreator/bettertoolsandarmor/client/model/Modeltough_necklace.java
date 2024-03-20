package net.mcreator.bettertoolsandarmor.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modeltough_necklace<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("better_tools", "modeltough_necklace"), "main");
	public final ModelPart tough_crystal;
	public final ModelPart string;

	public Modeltough_necklace(ModelPart root) {
		this.tough_crystal = root.getChild("tough_crystal");
		this.string = root.getChild("string");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition tough_crystal = partdefinition.addOrReplaceChild("tough_crystal", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition center_r1 = tough_crystal.addOrReplaceChild("center_r1",
				CubeListBuilder.create().texOffs(12, 0).addBox(0.0F, -6.0F, -6.0F, 1.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(39, 10).addBox(0.0F, -2.0F, -6.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(40, 9)
						.addBox(0.0F, -1.0F, -5.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 10).addBox(0.0F, -2.0F, 1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(39, 9)
						.addBox(0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(1, 1).addBox(0.0F, -8.0F, 1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 1)
						.addBox(0.0F, -7.0F, 1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 1).addBox(0.0F, -8.0F, -5.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(39, 1)
						.addBox(0.0F, -7.0F, -6.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition string = partdefinition.addOrReplaceChild("string", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition attach_right_r1 = string.addOrReplaceChild("attach_right_r1",
				CubeListBuilder.create().texOffs(58, 11).addBox(0.0F, -8.0F, 4.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(58, 10).addBox(-1.0F, -8.0F, 5.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(54, 8)
						.addBox(-4.0F, -8.0F, 6.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(58, 10).addBox(-5.0F, -8.0F, 5.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(43, 6)
						.addBox(-6.0F, -8.0F, -5.0F, 1.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(55, 11).addBox(-5.0F, -8.0F, -6.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(52, 15)
						.addBox(-4.0F, -8.0F, -7.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(52, 11).addBox(-1.0F, -8.0F, -6.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(53, 11)
						.addBox(0.0F, -8.0F, -5.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		tough_crystal.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		string.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}

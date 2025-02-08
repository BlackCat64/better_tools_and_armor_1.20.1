package net.mcreator.bettertoolsandarmor;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;

import net.mcreator.bettertoolsandarmor.block.entity.BetterToolsModBlockEntities;

@Mod.EventBusSubscriber(modid = BetterToolsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
	@SubscribeEvent
	public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(BetterToolsModBlockEntities.METAL_SIGN.get(), SignRenderer::new);
		event.registerBlockEntityRenderer(BetterToolsModBlockEntities.METAL_HANGING_SIGN.get(), HangingSignRenderer::new);
	}
}

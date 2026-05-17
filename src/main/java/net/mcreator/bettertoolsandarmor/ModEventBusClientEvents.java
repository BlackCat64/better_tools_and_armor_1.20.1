package net.mcreator.bettertoolsandarmor;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;

import net.mcreator.bettertoolsandarmor.block.entity.BetterToolsModBlockEntities;

@EventBusSubscriber(modid = BetterToolsMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
	@SubscribeEvent
	public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(BetterToolsModBlockEntities.METAL_SIGN.get(), SignRenderer::new);
		event.registerBlockEntityRenderer(BetterToolsModBlockEntities.METAL_HANGING_SIGN.get(), HangingSignRenderer::new);
	}
}

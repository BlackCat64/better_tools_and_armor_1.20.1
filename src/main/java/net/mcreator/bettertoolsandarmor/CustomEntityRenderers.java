package net.mcreator.bettertoolsandarmor;

import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModEntities;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CustomEntityRenderers {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			EntityRenderers.register(BetterToolsModEntities.CRYSTALLITE_PRISMARINE_ARROW.get(), TippableArrowRenderer::new);
		});
	}
}

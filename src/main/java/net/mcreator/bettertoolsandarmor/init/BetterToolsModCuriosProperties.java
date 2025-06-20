package net.mcreator.bettertoolsandarmor.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.item.ItemProperties;

import net.mcreator.bettertoolsandarmor.procedures.EnergyVialEnergyValueProcedure;
import net.mcreator.bettertoolsandarmor.procedures.EnergyVialActiveProcedure;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BetterToolsModCuriosProperties {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ItemProperties.register(BetterToolsModItems.ENERGY_VIAL.get(), new ResourceLocation("better_tools:energy_vial_energy"),
					(itemStackToRender, clientWorld, entity, itemEntityId) -> (float) EnergyVialEnergyValueProcedure.execute(itemStackToRender));
			ItemProperties.register(BetterToolsModItems.ENERGY_VIAL.get(), new ResourceLocation("better_tools:energy_vial_active"),
					(itemStackToRender, clientWorld, entity, itemEntityId) -> (float) EnergyVialActiveProcedure.execute(entity, itemStackToRender));
			ItemProperties.register(BetterToolsModItems.EMERALD_ENERGY_VIAL.get(), new ResourceLocation("better_tools:emerald_energy_vial_energy"),
					(itemStackToRender, clientWorld, entity, itemEntityId) -> (float) EnergyVialEnergyValueProcedure.execute(itemStackToRender));
			ItemProperties.register(BetterToolsModItems.EMERALD_ENERGY_VIAL.get(), new ResourceLocation("better_tools:emerald_energy_vial_active"),
					(itemStackToRender, clientWorld, entity, itemEntityId) -> (float) EnergyVialActiveProcedure.execute(entity, itemStackToRender));
			ItemProperties.register(BetterToolsModItems.NETHERITE_ENERGY_VIAL.get(), new ResourceLocation("better_tools:netherite_energy_vial_energy"),
					(itemStackToRender, clientWorld, entity, itemEntityId) -> (float) EnergyVialEnergyValueProcedure.execute(itemStackToRender));
			ItemProperties.register(BetterToolsModItems.NETHERITE_ENERGY_VIAL.get(), new ResourceLocation("better_tools:netherite_energy_vial_active"),
					(itemStackToRender, clientWorld, entity, itemEntityId) -> (float) EnergyVialActiveProcedure.execute(entity, itemStackToRender));
		});
	}
}

package net.mcreator.bettertoolsandarmor;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.client.renderer.Sheets;

import net.mcreator.bettertoolsandarmor.world.features.StructureFeature;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModWoodTypes;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModVillagerProfessions;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModTabs;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModSounds;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModPotions;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModParticleTypes;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModMenus;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModFeatures;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModEntities;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModEnchantments;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModBlocks;
import net.mcreator.bettertoolsandarmor.block.entity.BetterToolsModBlockEntities;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.AbstractMap;

@Mod("better_tools")
public class BetterToolsMod {
	public static final Logger LOGGER = LogManager.getLogger(BetterToolsMod.class);
	public static final String MODID = "better_tools";

	public BetterToolsMod() {
		// Start of user code block mod constructor
		// End of user code block mod constructor
		MinecraftForge.EVENT_BUS.register(this);
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		BetterToolsModSounds.REGISTRY.register(bus);
		BetterToolsModBlocks.REGISTRY.register(bus);
		BetterToolsModItems.REGISTRY.register(bus);
		BetterToolsModEntities.REGISTRY.register(bus);
		BetterToolsModEnchantments.REGISTRY.register(bus);
		BetterToolsModTabs.REGISTRY.register(bus);
		BetterToolsModFeatures.REGISTRY.register(bus);
		StructureFeature.REGISTRY.register(bus);
		BetterToolsModMobEffects.REGISTRY.register(bus);
		BetterToolsModPotions.REGISTRY.register(bus);
		BetterToolsModParticleTypes.REGISTRY.register(bus);
		BetterToolsModVillagerProfessions.PROFESSIONS.register(bus);
		BetterToolsModMenus.REGISTRY.register(bus);
		// Start of user code block mod init
		BetterToolsModBlockEntities.REGISTRY.register(bus);
		// End of user code block mod init
	}

	// Start of user code block mod methods
	@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents {
		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {
			Sheets.addWoodType(BetterToolsModWoodTypes.BLACK_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.BLUE_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.BROWN_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.CREAM_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.CYAN_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.DARK_ORANGE_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.DARK_PINK_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.DARK_RED_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.GRAY_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.GREEN_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.LIGHT_BLUE_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.LIGHT_GRAY_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.LIME_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.MAGENTA_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.ORANGE_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.PINK_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.PURPLE_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.RED_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.WHITE_METAL_SIGN);
			Sheets.addWoodType(BetterToolsModWoodTypes.YELLOW_METAL_SIGN);
		}
	}

	// End of user code block mod methods
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}

	private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

	public static void queueServerWork(int tick, Runnable action) {
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
			workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
	}

	@SubscribeEvent
	public void tick(TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
			workQueue.forEach(work -> {
				work.setValue(work.getValue() - 1);
				if (work.getValue() == 0)
					actions.add(work);
			});
			actions.forEach(e -> e.getKey().run());
			workQueue.removeAll(actions);
		}
	}
}

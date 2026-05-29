package net.mcreator.bettertoolsandarmor;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.fml.util.thread.SidedThreadGroups;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.util.Tuple;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.client.renderer.Sheets;

import net.mcreator.bettertoolsandarmor.world.features.StructureFeature;
import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.*;
import net.mcreator.bettertoolsandarmor.block.entity.BetterToolsModBlockEntities;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;

@Mod("better_tools")
public class BetterToolsMod {
	public static final Logger LOGGER = LogManager.getLogger(BetterToolsMod.class);
	public static final String MODID = "better_tools";

	public BetterToolsMod(IEventBus modEventBus) {
		// Start of user code block mod constructor
		// End of user code block mod constructor
		NeoForge.EVENT_BUS.register(this);
		modEventBus.addListener(this::registerNetworking);
		BetterToolsModSounds.REGISTRY.register(modEventBus);
		BetterToolsModBlocks.REGISTRY.register(modEventBus);
		BetterToolsModItems.REGISTRY.register(modEventBus);
		BetterToolsModEntities.REGISTRY.register(modEventBus);
		BetterToolsModTabs.REGISTRY.register(modEventBus);
		BetterToolsModVariables.ATTACHMENT_TYPES.register(modEventBus);
		BetterToolsModFeatures.REGISTRY.register(modEventBus);
		StructureFeature.REGISTRY.register(modEventBus);
		BetterToolsModPotions.REGISTRY.register(modEventBus);
		BetterToolsModMobEffects.REGISTRY.register(modEventBus);
		BetterToolsModMenus.REGISTRY.register(modEventBus);
		BetterToolsModParticleTypes.REGISTRY.register(modEventBus);
		BetterToolsModVillagerProfessions.PROFESSIONS.register(modEventBus);
		BetterToolsModAttributes.REGISTRY.register(modEventBus);
		// Start of user code block mod init
		CustomCreativeTabItems.REGISTRY.register(modEventBus);
		BetterToolsModBlockEntities.REGISTRY.register(modEventBus);
		modEventBus.addListener(this::commonSetup);
		// End of user code block mod init
	}

	// Start of user code block mod methods
	public static void registerMessages() {
		//		addNetworkMessage(EnergyVialGuiSyncMessage.class, EnergyVialGuiSyncMessage::buffer, EnergyVialGuiSyncMessage::decode, EnergyVialGuiSyncMessage::handler);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			registerMessages();
		});
	}

	@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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
			ModItemProperties.addCustomItemProperties();
			// enables Crystallite Bow pulling animation
		}
	}

	// End of user code block mod methods
	private static boolean networkingRegistered = false;
	private static final Map<CustomPacketPayload.Type<?>, NetworkMessage<?>> MESSAGES = new HashMap<>();

	private record NetworkMessage<T extends CustomPacketPayload>(StreamCodec<? extends FriendlyByteBuf, T> reader, IPayloadHandler<T> handler) {
	}

	public static <T extends CustomPacketPayload> void addNetworkMessage(CustomPacketPayload.Type<T> id, StreamCodec<? extends FriendlyByteBuf, T> reader, IPayloadHandler<T> handler) {
		if (networkingRegistered)
			throw new IllegalStateException("Cannot register new network messages after networking has been registered");
		MESSAGES.put(id, new NetworkMessage<>(reader, handler));
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private void registerNetworking(final RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar(MODID);
		MESSAGES.forEach((id, networkMessage) -> registrar.playBidirectional(id, ((NetworkMessage) networkMessage).reader(), ((NetworkMessage) networkMessage).handler()));
		networkingRegistered = true;
	}

	private static final Collection<Tuple<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

	public static void queueServerWork(int tick, Runnable action) {
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
			workQueue.add(new Tuple<>(action, tick));
	}

	@SubscribeEvent
	public void tick(ServerTickEvent.Post event) {
		List<Tuple<Runnable, Integer>> actions = new ArrayList<>();
		workQueue.forEach(work -> {
			work.setB(work.getB() - 1);
			if (work.getB() == 0)
				actions.add(work);
		});
		actions.forEach(e -> e.getA().run());
		workQueue.removeAll(actions);
	}
}
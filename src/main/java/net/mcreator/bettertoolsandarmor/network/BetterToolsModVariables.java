package net.mcreator.bettertoolsandarmor.network;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.HolderLookup;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import java.util.function.Supplier;

@EventBusSubscriber
public class BetterToolsModVariables {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, BetterToolsMod.MODID);
	public static final Supplier<AttachmentType<PlayerVariables>> PLAYER_VARIABLES = ATTACHMENT_TYPES.register("player_variables", () -> AttachmentType.serializable(() -> new PlayerVariables()).build());

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		BetterToolsMod.addNetworkMessage(SavedDataSyncMessage.TYPE, SavedDataSyncMessage.STREAM_CODEC, SavedDataSyncMessage::handleData);
		BetterToolsMod.addNetworkMessage(PlayerVariablesSyncMessage.TYPE, PlayerVariablesSyncMessage.STREAM_CODEC, PlayerVariablesSyncMessage::handleData);
	}

	@SubscribeEvent
	public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
		if (event.getEntity() instanceof ServerPlayer player)
			PacketDistributor.sendToPlayer(player, new PlayerVariablesSyncMessage(player.getData(PLAYER_VARIABLES)));
	}

	@SubscribeEvent
	public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
		if (event.getEntity() instanceof ServerPlayer player)
			PacketDistributor.sendToPlayer(player, new PlayerVariablesSyncMessage(player.getData(PLAYER_VARIABLES)));
	}

	@SubscribeEvent
	public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (event.getEntity() instanceof ServerPlayer player)
			PacketDistributor.sendToPlayer(player, new PlayerVariablesSyncMessage(player.getData(PLAYER_VARIABLES)));
	}

	@SubscribeEvent
	public static void onPlayerTickUpdateSyncPlayerVariables(PlayerTickEvent.Post event) {
		if (event.getEntity() instanceof ServerPlayer player && player.getData(PLAYER_VARIABLES)._syncDirty) {
			PacketDistributor.sendToPlayer(player, new PlayerVariablesSyncMessage(player.getData(PLAYER_VARIABLES)));
			player.getData(PLAYER_VARIABLES)._syncDirty = false;
		}
	}

	@SubscribeEvent
	public static void clonePlayer(PlayerEvent.Clone event) {
		PlayerVariables original = event.getOriginal().getData(PLAYER_VARIABLES);
		PlayerVariables clone = new PlayerVariables();
		clone.respawn_xp = original.respawn_xp;
		clone.extra_jumps = original.extra_jumps;
		clone.charms_equipped = original.charms_equipped;
		clone.last_on_ground_x = original.last_on_ground_x;
		clone.last_on_ground_y = original.last_on_ground_y;
		clone.last_on_ground_z = original.last_on_ground_z;
		clone.save_from_void_cooldown = original.save_from_void_cooldown;
		clone.last_food_eaten = original.last_food_eaten;
		clone.last_food_was_carbonated = original.last_food_was_carbonated;
		if (!event.isWasDeath()) {
			clone.time_since_last_hurt = original.time_since_last_hurt;
			clone.crystallite_emerald_heal_timer = original.crystallite_emerald_heal_timer;
			clone.critical_hit = original.critical_hit;
			clone.flaming_circlet_cooldown = original.flaming_circlet_cooldown;
			clone.time_since_last_attacked = original.time_since_last_attacked;
			clone.time_since_last_mined = original.time_since_last_mined;
			clone.last_mined_block = original.last_mined_block;
			clone.block_mining_combo = original.block_mining_combo;
			clone.stick_to_ceiling = original.stick_to_ceiling;
			clone.crystallite_honey_absorption_timer = original.crystallite_honey_absorption_timer;
			clone.time_since_last_jumped = original.time_since_last_jumped;
			clone.ender_titanium_boots_cooldown = original.ender_titanium_boots_cooldown;
			clone.time_since_non_carbonated_food_eaten = original.time_since_non_carbonated_food_eaten;
			clone.nature_ring_equipped = original.nature_ring_equipped;
			clone.effect_energy_timer = original.effect_energy_timer;
			clone.energy_vial_to_update = original.energy_vial_to_update;
			clone.effect_energy_cost = original.effect_energy_cost;
			clone.time_since_on_ground = original.time_since_on_ground;
			clone.time_since_shot_bow = original.time_since_shot_bow;
			clone.time_on_fire = original.time_on_fire;
			clone.nether_diamond_armor_fire_res_cooldown = original.nether_diamond_armor_fire_res_cooldown;
			clone.crystallite_redstone_sword_heal_cooldown = original.crystallite_redstone_sword_heal_cooldown;
			clone.crystallite_amethyst_ore_highlight_cooldown = original.crystallite_amethyst_ore_highlight_cooldown;
		}
		event.getEntity().setData(PLAYER_VARIABLES, clone);
	}

	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		if (event.getEntity() instanceof ServerPlayer player) {
			SavedData mapdata = MapVariables.get(player.level());
			SavedData worlddata = WorldVariables.get(player.level());
			if (mapdata != null)
				PacketDistributor.sendToPlayer(player, new SavedDataSyncMessage(0, mapdata));
			if (worlddata != null)
				PacketDistributor.sendToPlayer(player, new SavedDataSyncMessage(1, worlddata));
		}
	}

	@SubscribeEvent
	public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (event.getEntity() instanceof ServerPlayer player) {
			SavedData worlddata = WorldVariables.get(player.level());
			if (worlddata != null)
				PacketDistributor.sendToPlayer(player, new SavedDataSyncMessage(1, worlddata));
		}
	}

	@SubscribeEvent
	public static void onWorldTick(LevelTickEvent.Post event) {
		if (event.getLevel() instanceof ServerLevel level) {
			WorldVariables worldVariables = WorldVariables.get(level);
			if (worldVariables._syncDirty) {
				PacketDistributor.sendToPlayersInDimension(level, new SavedDataSyncMessage(1, worldVariables));
				worldVariables._syncDirty = false;
			}
			MapVariables mapVariables = MapVariables.get(level);
			if (mapVariables._syncDirty) {
				PacketDistributor.sendToAllPlayers(new SavedDataSyncMessage(0, mapVariables));
				mapVariables._syncDirty = false;
			}
		}
	}

	public static class WorldVariables extends SavedData {
		public static final String DATA_NAME = "better_tools_worldvars";
		boolean _syncDirty = false;

		public static WorldVariables load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
			WorldVariables data = new WorldVariables();
			data.read(tag, lookupProvider);
			return data;
		}

		public void read(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
		}

		@Override
		public CompoundTag save(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
			return nbt;
		}

		public void markSyncDirty() {
			this.setDirty();
			this._syncDirty = true;
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(LevelAccessor world) {
			if (world instanceof ServerLevel level) {
				return level.getDataStorage().computeIfAbsent(new SavedData.Factory<>(WorldVariables::new, WorldVariables::load), DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends SavedData {
		public static final String DATA_NAME = "better_tools_mapvars";
		boolean _syncDirty = false;
		public double crystallite_shimmer_timer = 0;
		public double stealth_armor_timer = 0;

		public static MapVariables load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
			MapVariables data = new MapVariables();
			data.read(tag, lookupProvider);
			return data;
		}

		public void read(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
			crystallite_shimmer_timer = nbt.getDouble("crystallite_shimmer_timer");
			stealth_armor_timer = nbt.getDouble("stealth_armor_timer");
		}

		@Override
		public CompoundTag save(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
			nbt.putDouble("crystallite_shimmer_timer", crystallite_shimmer_timer);
			nbt.putDouble("stealth_armor_timer", stealth_armor_timer);
			return nbt;
		}

		public void markSyncDirty() {
			this.setDirty();
			_syncDirty = true;
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(LevelAccessor world) {
			if (world instanceof ServerLevelAccessor serverLevelAcc) {
				return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(new SavedData.Factory<>(MapVariables::new, MapVariables::load), DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public record SavedDataSyncMessage(int dataType, SavedData data) implements CustomPacketPayload {
		public static final Type<SavedDataSyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(BetterToolsMod.MODID, "saved_data_sync"));
		public static final StreamCodec<RegistryFriendlyByteBuf, SavedDataSyncMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, SavedDataSyncMessage message) -> {
			buffer.writeInt(message.dataType);
			if (message.data != null)
				buffer.writeNbt(message.data.save(new CompoundTag(), buffer.registryAccess()));
		}, (RegistryFriendlyByteBuf buffer) -> {
			int dataType = buffer.readInt();
			CompoundTag nbt = buffer.readNbt();
			SavedData data = null;
			if (nbt != null) {
				data = dataType == 0 ? new MapVariables() : new WorldVariables();
				if (data instanceof MapVariables mapVariables)
					mapVariables.read(nbt, buffer.registryAccess());
				else if (data instanceof WorldVariables worldVariables)
					worldVariables.read(nbt, buffer.registryAccess());
			}
			return new SavedDataSyncMessage(dataType, data);
		});

		@Override
		public Type<SavedDataSyncMessage> type() {
			return TYPE;
		}

		public static void handleData(final SavedDataSyncMessage message, final IPayloadContext context) {
			if (context.flow() == PacketFlow.CLIENTBOUND && message.data != null) {
				context.enqueueWork(() -> {
					if (message.dataType == 0)
						MapVariables.clientSide.read(message.data.save(new CompoundTag(), context.player().registryAccess()), context.player().registryAccess());
					else
						WorldVariables.clientSide.read(message.data.save(new CompoundTag(), context.player().registryAccess()), context.player().registryAccess());
				}).exceptionally(e -> {
					context.connection().disconnect(Component.literal(e.getMessage()));
					return null;
				});
			}
		}
	}

	public static class PlayerVariables implements INBTSerializable<CompoundTag> {
		boolean _syncDirty = false;
		public double respawn_xp = 0.0;
		public double extra_jumps = 0.0;
		public double charms_equipped = 0.0;
		public double time_since_last_hurt = 0;
		public double crystallite_emerald_heal_timer = 0.0;
		public boolean critical_hit = false;
		public double last_on_ground_x = 0;
		public double last_on_ground_y = 0;
		public double last_on_ground_z = 0;
		public double save_from_void_cooldown = 0.0;
		public double flaming_circlet_cooldown = 0;
		public double time_since_last_attacked = 0;
		public double time_since_last_mined = 0;
		public BlockState last_mined_block = Blocks.AIR.defaultBlockState();
		public double block_mining_combo = 0;
		public boolean stick_to_ceiling = false;
		public double crystallite_honey_absorption_timer = 0;
		public double time_since_last_jumped = 0;
		public double ender_titanium_boots_cooldown = 0;
		public double time_since_non_carbonated_food_eaten = 0;
		public ItemStack last_food_eaten = ItemStack.EMPTY;
		public boolean last_food_was_carbonated = false;
		public boolean nature_ring_equipped = false;
		public double effect_energy_timer = 0;
		public ItemStack energy_vial_to_update = ItemStack.EMPTY;
		public double effect_energy_cost = 0;
		public double time_since_on_ground = 0;
		public double time_since_shot_bow = 0;
		public double time_on_fire = 0;
		public double nether_diamond_armor_fire_res_cooldown = 0;
		public double crystallite_redstone_sword_heal_cooldown = 0;
		public double crystallite_amethyst_ore_highlight_cooldown = 0;

		@Override
		public CompoundTag serializeNBT(HolderLookup.Provider lookupProvider) {
			CompoundTag nbt = new CompoundTag();
			nbt.putDouble("respawn_xp", respawn_xp);
			nbt.putDouble("extra_jumps", extra_jumps);
			nbt.putDouble("charms_equipped", charms_equipped);
			nbt.putDouble("time_since_last_hurt", time_since_last_hurt);
			nbt.putDouble("crystallite_emerald_heal_timer", crystallite_emerald_heal_timer);
			nbt.putBoolean("critical_hit", critical_hit);
			nbt.putDouble("last_on_ground_x", last_on_ground_x);
			nbt.putDouble("last_on_ground_y", last_on_ground_y);
			nbt.putDouble("last_on_ground_z", last_on_ground_z);
			nbt.putDouble("save_from_void_cooldown", save_from_void_cooldown);
			nbt.putDouble("flaming_circlet_cooldown", flaming_circlet_cooldown);
			nbt.putDouble("time_since_last_attacked", time_since_last_attacked);
			nbt.putDouble("time_since_last_mined", time_since_last_mined);
			nbt.put("last_mined_block", NbtUtils.writeBlockState(last_mined_block));
			nbt.putDouble("block_mining_combo", block_mining_combo);
			nbt.putBoolean("stick_to_ceiling", stick_to_ceiling);
			nbt.putDouble("crystallite_honey_absorption_timer", crystallite_honey_absorption_timer);
			nbt.putDouble("time_since_last_jumped", time_since_last_jumped);
			nbt.putDouble("ender_titanium_boots_cooldown", ender_titanium_boots_cooldown);
			nbt.putDouble("time_since_non_carbonated_food_eaten", time_since_non_carbonated_food_eaten);
			nbt.put("last_food_eaten", last_food_eaten.saveOptional(lookupProvider));
			nbt.putBoolean("last_food_was_carbonated", last_food_was_carbonated);
			nbt.putBoolean("nature_ring_equipped", nature_ring_equipped);
			nbt.putDouble("effect_energy_timer", effect_energy_timer);
			nbt.put("energy_vial_to_update", energy_vial_to_update.saveOptional(lookupProvider));
			nbt.putDouble("effect_energy_cost", effect_energy_cost);
			nbt.putDouble("time_since_on_ground", time_since_on_ground);
			nbt.putDouble("time_since_shot_bow", time_since_shot_bow);
			nbt.putDouble("time_on_fire", time_on_fire);
			nbt.putDouble("nether_diamond_armor_fire_res_cooldown", nether_diamond_armor_fire_res_cooldown);
			nbt.putDouble("crystallite_redstone_sword_heal_cooldown", crystallite_redstone_sword_heal_cooldown);
			nbt.putDouble("crystallite_amethyst_ore_highlight_cooldown", crystallite_amethyst_ore_highlight_cooldown);
			return nbt;
		}

		@Override
		public void deserializeNBT(HolderLookup.Provider lookupProvider, CompoundTag nbt) {
			respawn_xp = nbt.getDouble("respawn_xp");
			extra_jumps = nbt.getDouble("extra_jumps");
			charms_equipped = nbt.getDouble("charms_equipped");
			time_since_last_hurt = nbt.getDouble("time_since_last_hurt");
			crystallite_emerald_heal_timer = nbt.getDouble("crystallite_emerald_heal_timer");
			critical_hit = nbt.getBoolean("critical_hit");
			last_on_ground_x = nbt.getDouble("last_on_ground_x");
			last_on_ground_y = nbt.getDouble("last_on_ground_y");
			last_on_ground_z = nbt.getDouble("last_on_ground_z");
			save_from_void_cooldown = nbt.getDouble("save_from_void_cooldown");
			flaming_circlet_cooldown = nbt.getDouble("flaming_circlet_cooldown");
			time_since_last_attacked = nbt.getDouble("time_since_last_attacked");
			time_since_last_mined = nbt.getDouble("time_since_last_mined");
			last_mined_block = NbtUtils.readBlockState(lookupProvider.lookupOrThrow(BuiltInRegistries.BLOCK.key()), nbt.getCompound("last_mined_block"));
			block_mining_combo = nbt.getDouble("block_mining_combo");
			stick_to_ceiling = nbt.getBoolean("stick_to_ceiling");
			crystallite_honey_absorption_timer = nbt.getDouble("crystallite_honey_absorption_timer");
			time_since_last_jumped = nbt.getDouble("time_since_last_jumped");
			ender_titanium_boots_cooldown = nbt.getDouble("ender_titanium_boots_cooldown");
			time_since_non_carbonated_food_eaten = nbt.getDouble("time_since_non_carbonated_food_eaten");
			last_food_eaten = ItemStack.parseOptional(lookupProvider, nbt.getCompound("last_food_eaten"));
			last_food_was_carbonated = nbt.getBoolean("last_food_was_carbonated");
			nature_ring_equipped = nbt.getBoolean("nature_ring_equipped");
			effect_energy_timer = nbt.getDouble("effect_energy_timer");
			energy_vial_to_update = ItemStack.parseOptional(lookupProvider, nbt.getCompound("energy_vial_to_update"));
			effect_energy_cost = nbt.getDouble("effect_energy_cost");
			time_since_on_ground = nbt.getDouble("time_since_on_ground");
			time_since_shot_bow = nbt.getDouble("time_since_shot_bow");
			time_on_fire = nbt.getDouble("time_on_fire");
			nether_diamond_armor_fire_res_cooldown = nbt.getDouble("nether_diamond_armor_fire_res_cooldown");
			crystallite_redstone_sword_heal_cooldown = nbt.getDouble("crystallite_redstone_sword_heal_cooldown");
			crystallite_amethyst_ore_highlight_cooldown = nbt.getDouble("crystallite_amethyst_ore_highlight_cooldown");
		}

		public void markSyncDirty() {
			_syncDirty = true;
		}
	}

	public record PlayerVariablesSyncMessage(PlayerVariables data) implements CustomPacketPayload {
		public static final Type<PlayerVariablesSyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(BetterToolsMod.MODID, "player_variables_sync"));
		public static final StreamCodec<RegistryFriendlyByteBuf, PlayerVariablesSyncMessage> STREAM_CODEC = StreamCodec
				.of((RegistryFriendlyByteBuf buffer, PlayerVariablesSyncMessage message) -> buffer.writeNbt(message.data().serializeNBT(buffer.registryAccess())), (RegistryFriendlyByteBuf buffer) -> {
					PlayerVariablesSyncMessage message = new PlayerVariablesSyncMessage(new PlayerVariables());
					message.data.deserializeNBT(buffer.registryAccess(), buffer.readNbt());
					return message;
				});

		@Override
		public Type<PlayerVariablesSyncMessage> type() {
			return TYPE;
		}

		public static void handleData(final PlayerVariablesSyncMessage message, final IPayloadContext context) {
			if (context.flow() == PacketFlow.CLIENTBOUND && message.data != null) {
				context.enqueueWork(() -> context.player().getData(PLAYER_VARIABLES).deserializeNBT(context.player().registryAccess(), message.data.serializeNBT(context.player().registryAccess()))).exceptionally(e -> {
					context.connection().disconnect(Component.literal(e.getMessage()));
					return null;
				});
			}
		}
	}
}
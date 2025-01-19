package net.mcreator.bettertoolsandarmor.network;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterToolsModVariables {
	public static boolean being_damaged_flag = false;

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		BetterToolsMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			clone.respawn_xp = original.respawn_xp;
			clone.extra_jumps = original.extra_jumps;
			clone.charms_equipped = original.charms_equipped;
			clone.last_on_ground_x = original.last_on_ground_x;
			clone.last_on_ground_y = original.last_on_ground_y;
			clone.last_on_ground_z = original.last_on_ground_z;
			clone.save_from_void_cooldown = original.save_from_void_cooldown;
			clone.attack_damage = original.attack_damage;
			clone.attack_damage_modifier = original.attack_damage_modifier;
			clone.knockback_resistance = original.knockback_resistance;
			clone.knockback_resistance_modifier = original.knockback_resistance_modifier;
			clone.last_death_x = original.last_death_x;
			clone.last_death_y = original.last_death_y;
			clone.last_death_z = original.last_death_z;
			clone.last_food_eaten = original.last_food_eaten;
			clone.last_food_was_carbonated = original.last_food_was_carbonated;
			if (!event.isWasDeath()) {
				clone.time_since_last_hurt = original.time_since_last_hurt;
				clone.crystallite_emerald_heal_timer = original.crystallite_emerald_heal_timer;
				clone.blocks_broken_with_sculk_crystallite_pickaxe = original.blocks_broken_with_sculk_crystallite_pickaxe;
				clone.critical_hit = original.critical_hit;
				clone.fall_time = original.fall_time;
				clone.crystallite_amethyst_absorption_timer = original.crystallite_amethyst_absorption_timer;
				clone.flaming_circlet_cooldown = original.flaming_circlet_cooldown;
				clone.time_since_last_attacked = original.time_since_last_attacked;
				clone.time_since_last_mined = original.time_since_last_mined;
				clone.last_mined_block = original.last_mined_block;
				clone.block_mining_combo = original.block_mining_combo;
				clone.stick_to_ceiling = original.stick_to_ceiling;
				clone.crystallite_honey_absorption_timer = original.crystallite_honey_absorption_timer;
				clone.time_since_last_jumped = original.time_since_last_jumped;
				clone.smelting_touch_item_to_smelt = original.smelting_touch_item_to_smelt;
				clone.smelting_touch_item_to_drop = original.smelting_touch_item_to_drop;
				clone.ender_titanium_boots_cooldown = original.ender_titanium_boots_cooldown;
				clone.time_since_non_carbonated_food_eaten = original.time_since_non_carbonated_food_eaten;
				clone.fall_start_y = original.fall_start_y;
				clone.nature_ring_equipped = original.nature_ring_equipped;
				clone.is_in_cold_biome = original.is_in_cold_biome;
				clone.is_in_sunlight = original.is_in_sunlight;
			}
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("better_tools", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public double respawn_xp = 0.0;
		public double extra_jumps = 0.0;
		public double charms_equipped = 0.0;
		public double time_since_last_hurt = 0;
		public double crystallite_emerald_heal_timer = 0.0;
		public double blocks_broken_with_sculk_crystallite_pickaxe = 0;
		public boolean critical_hit = false;
		public double fall_time = 0.0;
		public double crystallite_amethyst_absorption_timer = 300.0;
		public double last_on_ground_x = 0;
		public double last_on_ground_y = 0;
		public double last_on_ground_z = 0;
		public double save_from_void_cooldown = 0.0;
		public String attack_damage = "\"\"";
		public String attack_damage_modifier = "\"\"";
		public String knockback_resistance = "\"\"";
		public String knockback_resistance_modifier = "\"\"";
		public double flaming_circlet_cooldown = 0;
		public double time_since_last_attacked = 0;
		public double time_since_last_mined = 0;
		public BlockState last_mined_block = Blocks.AIR.defaultBlockState();
		public double block_mining_combo = 0;
		public boolean stick_to_ceiling = false;
		public double crystallite_honey_absorption_timer = 0;
		public double time_since_last_jumped = 0;
		public ItemStack smelting_touch_item_to_smelt = ItemStack.EMPTY;
		public ItemStack smelting_touch_item_to_drop = ItemStack.EMPTY;
		public double ender_titanium_boots_cooldown = 0;
		public double last_death_x = 0;
		public double last_death_y = 0;
		public double last_death_z = 0;
		public double time_since_non_carbonated_food_eaten = 0;
		public ItemStack last_food_eaten = ItemStack.EMPTY;
		public boolean last_food_was_carbonated = false;
		public double fall_start_y = 0;
		public boolean nature_ring_equipped = false;
		public boolean is_in_cold_biome = false;
		public boolean is_in_sunlight = false;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				BetterToolsMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putDouble("respawn_xp", respawn_xp);
			nbt.putDouble("extra_jumps", extra_jumps);
			nbt.putDouble("charms_equipped", charms_equipped);
			nbt.putDouble("time_since_last_hurt", time_since_last_hurt);
			nbt.putDouble("crystallite_emerald_heal_timer", crystallite_emerald_heal_timer);
			nbt.putDouble("blocks_broken_with_sculk_crystallite_pickaxe", blocks_broken_with_sculk_crystallite_pickaxe);
			nbt.putBoolean("critical_hit", critical_hit);
			nbt.putDouble("fall_time", fall_time);
			nbt.putDouble("crystallite_amethyst_absorption_timer", crystallite_amethyst_absorption_timer);
			nbt.putDouble("last_on_ground_x", last_on_ground_x);
			nbt.putDouble("last_on_ground_y", last_on_ground_y);
			nbt.putDouble("last_on_ground_z", last_on_ground_z);
			nbt.putDouble("save_from_void_cooldown", save_from_void_cooldown);
			nbt.putString("attack_damage", attack_damage);
			nbt.putString("attack_damage_modifier", attack_damage_modifier);
			nbt.putString("knockback_resistance", knockback_resistance);
			nbt.putString("knockback_resistance_modifier", knockback_resistance_modifier);
			nbt.putDouble("flaming_circlet_cooldown", flaming_circlet_cooldown);
			nbt.putDouble("time_since_last_attacked", time_since_last_attacked);
			nbt.putDouble("time_since_last_mined", time_since_last_mined);
			nbt.put("last_mined_block", NbtUtils.writeBlockState(last_mined_block));
			nbt.putDouble("block_mining_combo", block_mining_combo);
			nbt.putBoolean("stick_to_ceiling", stick_to_ceiling);
			nbt.putDouble("crystallite_honey_absorption_timer", crystallite_honey_absorption_timer);
			nbt.putDouble("time_since_last_jumped", time_since_last_jumped);
			nbt.put("smelting_touch_item_to_smelt", smelting_touch_item_to_smelt.save(new CompoundTag()));
			nbt.put("smelting_touch_item_to_drop", smelting_touch_item_to_drop.save(new CompoundTag()));
			nbt.putDouble("ender_titanium_boots_cooldown", ender_titanium_boots_cooldown);
			nbt.putDouble("last_death_x", last_death_x);
			nbt.putDouble("last_death_y", last_death_y);
			nbt.putDouble("last_death_z", last_death_z);
			nbt.putDouble("time_since_non_carbonated_food_eaten", time_since_non_carbonated_food_eaten);
			nbt.put("last_food_eaten", last_food_eaten.save(new CompoundTag()));
			nbt.putBoolean("last_food_was_carbonated", last_food_was_carbonated);
			nbt.putDouble("fall_start_y", fall_start_y);
			nbt.putBoolean("nature_ring_equipped", nature_ring_equipped);
			nbt.putBoolean("is_in_cold_biome", is_in_cold_biome);
			nbt.putBoolean("is_in_sunlight", is_in_sunlight);
			return nbt;
		}

		public void readNBT(Tag tag) {
			CompoundTag nbt = (CompoundTag) tag;
			respawn_xp = nbt.getDouble("respawn_xp");
			extra_jumps = nbt.getDouble("extra_jumps");
			charms_equipped = nbt.getDouble("charms_equipped");
			time_since_last_hurt = nbt.getDouble("time_since_last_hurt");
			crystallite_emerald_heal_timer = nbt.getDouble("crystallite_emerald_heal_timer");
			blocks_broken_with_sculk_crystallite_pickaxe = nbt.getDouble("blocks_broken_with_sculk_crystallite_pickaxe");
			critical_hit = nbt.getBoolean("critical_hit");
			fall_time = nbt.getDouble("fall_time");
			crystallite_amethyst_absorption_timer = nbt.getDouble("crystallite_amethyst_absorption_timer");
			last_on_ground_x = nbt.getDouble("last_on_ground_x");
			last_on_ground_y = nbt.getDouble("last_on_ground_y");
			last_on_ground_z = nbt.getDouble("last_on_ground_z");
			save_from_void_cooldown = nbt.getDouble("save_from_void_cooldown");
			attack_damage = nbt.getString("attack_damage");
			attack_damage_modifier = nbt.getString("attack_damage_modifier");
			knockback_resistance = nbt.getString("knockback_resistance");
			knockback_resistance_modifier = nbt.getString("knockback_resistance_modifier");
			flaming_circlet_cooldown = nbt.getDouble("flaming_circlet_cooldown");
			time_since_last_attacked = nbt.getDouble("time_since_last_attacked");
			time_since_last_mined = nbt.getDouble("time_since_last_mined");
			last_mined_block = NbtUtils.readBlockState(BuiltInRegistries.BLOCK.asLookup(), nbt.getCompound("last_mined_block"));
			block_mining_combo = nbt.getDouble("block_mining_combo");
			stick_to_ceiling = nbt.getBoolean("stick_to_ceiling");
			crystallite_honey_absorption_timer = nbt.getDouble("crystallite_honey_absorption_timer");
			time_since_last_jumped = nbt.getDouble("time_since_last_jumped");
			smelting_touch_item_to_smelt = ItemStack.of(nbt.getCompound("smelting_touch_item_to_smelt"));
			smelting_touch_item_to_drop = ItemStack.of(nbt.getCompound("smelting_touch_item_to_drop"));
			ender_titanium_boots_cooldown = nbt.getDouble("ender_titanium_boots_cooldown");
			last_death_x = nbt.getDouble("last_death_x");
			last_death_y = nbt.getDouble("last_death_y");
			last_death_z = nbt.getDouble("last_death_z");
			time_since_non_carbonated_food_eaten = nbt.getDouble("time_since_non_carbonated_food_eaten");
			last_food_eaten = ItemStack.of(nbt.getCompound("last_food_eaten"));
			last_food_was_carbonated = nbt.getBoolean("last_food_was_carbonated");
			fall_start_y = nbt.getDouble("fall_start_y");
			nature_ring_equipped = nbt.getBoolean("nature_ring_equipped");
			is_in_cold_biome = nbt.getBoolean("is_in_cold_biome");
			is_in_sunlight = nbt.getBoolean("is_in_sunlight");
		}
	}

	public static class PlayerVariablesSyncMessage {
		private final PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
					variables.respawn_xp = message.data.respawn_xp;
					variables.extra_jumps = message.data.extra_jumps;
					variables.charms_equipped = message.data.charms_equipped;
					variables.time_since_last_hurt = message.data.time_since_last_hurt;
					variables.crystallite_emerald_heal_timer = message.data.crystallite_emerald_heal_timer;
					variables.blocks_broken_with_sculk_crystallite_pickaxe = message.data.blocks_broken_with_sculk_crystallite_pickaxe;
					variables.critical_hit = message.data.critical_hit;
					variables.fall_time = message.data.fall_time;
					variables.crystallite_amethyst_absorption_timer = message.data.crystallite_amethyst_absorption_timer;
					variables.last_on_ground_x = message.data.last_on_ground_x;
					variables.last_on_ground_y = message.data.last_on_ground_y;
					variables.last_on_ground_z = message.data.last_on_ground_z;
					variables.save_from_void_cooldown = message.data.save_from_void_cooldown;
					variables.attack_damage = message.data.attack_damage;
					variables.attack_damage_modifier = message.data.attack_damage_modifier;
					variables.knockback_resistance = message.data.knockback_resistance;
					variables.knockback_resistance_modifier = message.data.knockback_resistance_modifier;
					variables.flaming_circlet_cooldown = message.data.flaming_circlet_cooldown;
					variables.time_since_last_attacked = message.data.time_since_last_attacked;
					variables.time_since_last_mined = message.data.time_since_last_mined;
					variables.last_mined_block = message.data.last_mined_block;
					variables.block_mining_combo = message.data.block_mining_combo;
					variables.stick_to_ceiling = message.data.stick_to_ceiling;
					variables.crystallite_honey_absorption_timer = message.data.crystallite_honey_absorption_timer;
					variables.time_since_last_jumped = message.data.time_since_last_jumped;
					variables.smelting_touch_item_to_smelt = message.data.smelting_touch_item_to_smelt;
					variables.smelting_touch_item_to_drop = message.data.smelting_touch_item_to_drop;
					variables.ender_titanium_boots_cooldown = message.data.ender_titanium_boots_cooldown;
					variables.last_death_x = message.data.last_death_x;
					variables.last_death_y = message.data.last_death_y;
					variables.last_death_z = message.data.last_death_z;
					variables.time_since_non_carbonated_food_eaten = message.data.time_since_non_carbonated_food_eaten;
					variables.last_food_eaten = message.data.last_food_eaten;
					variables.last_food_was_carbonated = message.data.last_food_was_carbonated;
					variables.fall_start_y = message.data.fall_start_y;
					variables.nature_ring_equipped = message.data.nature_ring_equipped;
					variables.is_in_cold_biome = message.data.is_in_cold_biome;
					variables.is_in_sunlight = message.data.is_in_sunlight;
				}
			});
			context.setPacketHandled(true);
		}
	}
}

package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@EventBusSubscriber
public class EnderTitaniumArmorSaveFromVoid2Procedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingIncomingDamageEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getSource(), event.getEntity());
		}
	}

	public static void execute(LevelAccessor world, DamageSource damagesource, Entity entity) {
		execute(null, world, damagesource, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, DamageSource damagesource, Entity entity) {
		if (damagesource == null || entity == null)
			return;
		if (damagesource.is(DamageTypes.FELL_OUT_OF_WORLD)) {
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.END_TITANIUM_HELMET.get()
					&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.END_TITANIUM_CHESTPLATE.get()
					&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.END_TITANIUM_LEGGINGS.get()
					&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.END_TITANIUM_BOOTS.get()) {
				if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).save_from_void_cooldown == 0) {
					if (event instanceof ICancellableEvent _cancellable) {
						_cancellable.setCanceled(true);
					}
					entity.fallDistance = 0;
					if (!world.getBlockState(BlockPos.containing(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_x, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_y - 1,
							entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_z)).canOcclude()) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL,
											new Vec3(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_x, (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_y - 1),
													entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_z),
											Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"fill ~-1 ~ ~-1 ~1 ~ ~1 end_stone destroy");
					}
					{
						Entity _ent = entity;
						_ent.teleportTo(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_x, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_y,
								entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_z);
						if (_ent instanceof ServerPlayer _serverPlayer)
							_serverPlayer.connection.teleport(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_x, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_y,
									entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_z, _ent.getYRot(), _ent.getXRot());
					}
					if (entity instanceof Player _player)
						_player.getCooldowns().addCooldown(BetterToolsModItems.END_TITANIUM_HELMET.get(), 12000);
					if (entity instanceof Player _player)
						_player.getCooldowns().addCooldown(BetterToolsModItems.END_TITANIUM_CHESTPLATE.get(), 12000);
					if (entity instanceof Player _player)
						_player.getCooldowns().addCooldown(BetterToolsModItems.END_TITANIUM_LEGGINGS.get(), 12000);
					if (entity instanceof Player _player)
						_player.getCooldowns().addCooldown(BetterToolsModItems.END_TITANIUM_BOOTS.get(), 12000);
					{
						BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
						_vars.save_from_void_cooldown = 12000;
						_vars.markSyncDirty();
					}
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.DRAGON_BREATH, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_x, (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_y + 0.25),
								entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_z, 8, 3, 0.1, 1, 0.1);
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_x, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_y,
									entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.totem.use")), SoundSource.PLAYERS, (float) 0.5, 1);
						} else {
							_level.playLocalSound(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_x, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_y,
									entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.totem.use")), SoundSource.PLAYERS, (float) 0.5, 1, false);
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_x, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_y,
									entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.enderman.teleport")), SoundSource.PLAYERS, 1, 1);
						} else {
							_level.playLocalSound(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_x, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_y,
									entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.enderman.teleport")), SoundSource.PLAYERS, 1, 1, false);
						}
					}
					if (entity instanceof LivingEntity _entity)
						_entity.setHealth(2);
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1, false, false));
					if (world instanceof ServerLevel _level) {
						(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).hurtAndBreak(130, _level, null, _stkprov -> {
						});
					}
					if (world instanceof ServerLevel _level) {
						(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).hurtAndBreak(150, _level, null, _stkprov -> {
						});
					}
					if (world instanceof ServerLevel _level) {
						(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).hurtAndBreak(160, _level, null, _stkprov -> {
						});
					}
					if (world instanceof ServerLevel _level) {
						(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).hurtAndBreak(110, _level, null, _stkprov -> {
						});
					}
					if (entity instanceof ServerPlayer _player) {
						AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:saved_from_void_adv"));
						if (_adv != null) {
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
				}
			}
		}
	}
}
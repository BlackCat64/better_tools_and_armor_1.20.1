package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@EventBusSubscriber
public class NetherDiamondArmorFireProcedureProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean crystallite = false;
		double chance = 0;
		double armor_pieces = 0;
		double i = 0;
		double time = 0;
		if (((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:flaming_armor")))
				|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:flaming_armor")))
				|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:flaming_armor")))
				|| (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:flaming_armor")))) && entity.isOnFire()
				&& !(entity instanceof LivingEntity _livEnt9 && _livEnt9.hasEffect(MobEffects.FIRE_RESISTANCE))) {
			for (int index0 = 0; index0 < 4; index0++) {
				if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(new Object() {
					public static EquipmentSlot armorSlotByIndex(int _slotindex) {
						for (EquipmentSlot _slot : EquipmentSlot.values()) {
							if (_slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR && _slot.getIndex() == _slotindex) {
								return _slot;
							}
						}
						throw new IllegalArgumentException("Invalid slot index: " + _slotindex);
					}
				}.armorSlotByIndex((int) i)) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:flaming_armor")))) {
					armor_pieces = armor_pieces + 1;
					if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(new Object() {
						public static EquipmentSlot armorSlotByIndex(int _slotindex) {
							for (EquipmentSlot _slot : EquipmentSlot.values()) {
								if (_slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR && _slot.getIndex() == _slotindex) {
									return _slot;
								}
							}
							throw new IllegalArgumentException("Invalid slot index: " + _slotindex);
						}
					}.armorSlotByIndex((int) i)) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:nether_diamond_upgraded_crystallite_items")))) {
						crystallite = true;
					}
				}
				i = i + 1;
			}
			if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).nether_diamond_armor_fire_res_cooldown == 0) {
				if (crystallite) {
					time = armor_pieces == 4 ? 300 : armor_pieces * 60;
				} else if (armor_pieces == 4) {
					time = 100;
				}
			}
			if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_on_fire >= (6 - armor_pieces) * 20 || time > 0) {
				entity.clearFire();
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.extinguish_fire")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.extinguish_fire")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
				if (time > 0) {
					if ((entity.level().dimension()) == Level.NETHER) {
						time = time * 2;
					}
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, (int) time, 0));
					if (entity instanceof ServerPlayer _player) {
						AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:nether_diamond_armor_adv"));
						if (_adv != null) {
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
					{
						BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
						_vars.nether_diamond_armor_fire_res_cooldown = 1200;
						_vars.syncPlayerVariables(entity);
					}
					i = 0;
					for (int index1 = 0; index1 < 4; index1++) {
						if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(new Object() {
							public static EquipmentSlot armorSlotByIndex(int _slotindex) {
								for (EquipmentSlot _slot : EquipmentSlot.values()) {
									if (_slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR && _slot.getIndex() == _slotindex) {
										return _slot;
									}
								}
								throw new IllegalArgumentException("Invalid slot index: " + _slotindex);
							}
						}.armorSlotByIndex((int) i)) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:flaming_armor")))) {
							if (world instanceof ServerLevel _level) {
								(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(new Object() {
									public static EquipmentSlot armorSlotByIndex(int _slotindex) {
										for (EquipmentSlot _slot : EquipmentSlot.values()) {
											if (_slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR && _slot.getIndex() == _slotindex) {
												return _slot;
											}
										}
										throw new IllegalArgumentException("Invalid slot index: " + _slotindex);
									}
								}.armorSlotByIndex((int) i)) : ItemStack.EMPTY).hurtAndBreak(1, _level, null, _stkprov -> {
								});
							}
							if (entity instanceof Player _player)
								_player.getCooldowns().addCooldown((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(new Object() {
									public static EquipmentSlot armorSlotByIndex(int _slotindex) {
										for (EquipmentSlot _slot : EquipmentSlot.values()) {
											if (_slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR && _slot.getIndex() == _slotindex) {
												return _slot;
											}
										}
										throw new IllegalArgumentException("Invalid slot index: " + _slotindex);
									}
								}.armorSlotByIndex((int) i)) : ItemStack.EMPTY).getItem(), 1200);
						}
						i = i + 1;
					}
				}
			}
		}
	}
}

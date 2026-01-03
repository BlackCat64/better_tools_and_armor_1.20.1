package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class EffectEnergyApplyCostProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double timer = 0;
		double absorption_limit = 0;
		{
			double _setval = CalculateEffectEnergyCostProcedure.execute(entity, itemstack);
			entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.effect_energy_cost = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (EnergyVialActiveProcedure.execute(entity, itemstack) > 0) {
			if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).effect_energy_timer <= 0) {
				{
					double _setval = 100;
					entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.effect_energy_timer = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
					itemstack.getOrCreateTag().putDouble("energy",
							Math.max(itemstack.getOrCreateTag().getDouble("energy") - (entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).effect_energy_cost, 0));
				}
				if (itemstack.getOrCreateTag().getBoolean("helmet_active")) {
					ApplyHelmetEffectProcedure.execute(entity);
				}
				if (itemstack.getOrCreateTag().getBoolean("chestplate_active")) {
					ApplyChestplateEffectProcedure.execute(entity);
				}
				if (itemstack.getOrCreateTag().getBoolean("leggings_active")) {
					ApplyLeggingsEffectProcedure.execute(entity);
				}
				if (itemstack.getOrCreateTag().getBoolean("boots_active")) {
					ApplyBootsEffectProcedure.execute(entity);
				}
				if (EnergyVialActiveArmorPiecesProcedure.execute(entity, itemstack) == 4) {
					if (IsWearingGlassArmorFullSetProcedure.execute(entity)) {
						GlassArmorProcedureProcedure.execute(entity);
					}
					if (!(entity instanceof ServerPlayer _plr13 && _plr13.level() instanceof ServerLevel
							&& _plr13.getAdvancements().getOrStartProgress(_plr13.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:all_diamond_effect_armor_adv"))).isDone())) {
						if (entity instanceof ServerPlayer _player) {
							Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:effect_armor_full_set_adv"));
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
						if (itemstack.getItem() == BetterToolsModItems.EMERALD_ENERGY_VIAL.get()) {
							if (entity instanceof ServerPlayer _player) {
								Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:emerald_energy_vial_adv"));
								AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
								if (!_ap.isDone()) {
									for (String criteria : _ap.getRemainingCriteria())
										_player.getAdvancements().award(_adv, criteria);
								}
							}
						} else if (itemstack.getItem() == BetterToolsModItems.NETHERITE_ENERGY_VIAL.get()) {
							if (entity instanceof ServerPlayer _player) {
								Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:emerald_energy_vial_adv"));
								AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
								if (!_ap.isDone()) {
									for (String criteria : _ap.getRemainingCriteria())
										_player.getAdvancements().award(_adv, criteria);
								}
							}
							if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:diamond_encrusted_armor")))
									&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:diamond_encrusted_armor")))
									&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:diamond_encrusted_armor")))
									&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:diamond_encrusted_armor")))) {
								if (entity instanceof ServerPlayer _player) {
									Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("better_tools:all_diamond_effect_armor_adv"));
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
			} else {
				{
					double _setval = (entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).effect_energy_timer - 1;
					entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.effect_energy_timer = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		} else {
			{
				double _setval = 0;
				entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.effect_energy_timer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}

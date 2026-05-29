package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

public class EffectEnergyApplyCostProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double timer = 0;
		double absorption_limit = 0;
		{
			BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
			_vars.effect_energy_cost = CalculateEffectEnergyCostProcedure.execute(entity, itemstack);
			_vars.markSyncDirty();
		}
		if (EnergyVialActiveProcedure.execute(entity, itemstack) > 0) {
			if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).effect_energy_timer <= 0) {
				{
					BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
					_vars.effect_energy_timer = 100;
					_vars.markSyncDirty();
				}
				if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
					{
						final String _tagName = "energy";
						final double _tagValue = Math.max(itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("energy") - entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).effect_energy_cost, 0);
						CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
					}
				}
				if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("helmet_active")) {
					ApplyHelmetEffectProcedure.execute(entity);
				}
				if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("chestplate_active")) {
					ApplyChestplateEffectProcedure.execute(entity);
				}
				if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("leggings_active")) {
					ApplyLeggingsEffectProcedure.execute(entity);
				}
				if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("boots_active")) {
					ApplyBootsEffectProcedure.execute(entity);
				}
				if (EnergyVialActiveArmorPiecesProcedure.execute(entity, itemstack) == 4) {
					if (IsWearingGlassArmorFullSetProcedure.execute(entity)) {
						GlassArmorProcedureProcedure.execute(entity);
					}
					if (!(entity instanceof ServerPlayer _plr13 && _plr13.level() instanceof ServerLevel
							&& _plr13.getAdvancements().getOrStartProgress(_plr13.server.getAdvancements().get(ResourceLocation.parse("better_tools:all_diamond_effect_armor_adv"))).isDone())) {
						if (entity instanceof ServerPlayer _player) {
							AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:effect_armor_full_set_adv"));
							if (_adv != null) {
								AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
								if (!_ap.isDone()) {
									for (String criteria : _ap.getRemainingCriteria())
										_player.getAdvancements().award(_adv, criteria);
								}
							}
						}
						if (true) {
							if (entity instanceof ServerPlayer _player) {
								AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:emerald_energy_vial_adv"));
								if (_adv != null) {
									AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
									if (!_ap.isDone()) {
										for (String criteria : _ap.getRemainingCriteria())
											_player.getAdvancements().award(_adv, criteria);
									}
								}
							}
						} else if (true) {
							if (entity instanceof ServerPlayer _player) {
								AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:emerald_energy_vial_adv"));
								if (_adv != null) {
									AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
									if (!_ap.isDone()) {
										for (String criteria : _ap.getRemainingCriteria())
											_player.getAdvancements().award(_adv, criteria);
									}
								}
							}
							if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:diamond_encrusted_armor")))
									&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:diamond_encrusted_armor")))
									&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:diamond_encrusted_armor")))
									&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:diamond_encrusted_armor")))) {
								if (entity instanceof ServerPlayer _player) {
									AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:all_diamond_effect_armor_adv"));
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
			} else {
				{
					BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
					_vars.effect_energy_timer = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).effect_energy_timer - 1;
					_vars.markSyncDirty();
				}
			}
		} else {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.effect_energy_timer = 0;
				_vars.markSyncDirty();
			}
		}
	}
}
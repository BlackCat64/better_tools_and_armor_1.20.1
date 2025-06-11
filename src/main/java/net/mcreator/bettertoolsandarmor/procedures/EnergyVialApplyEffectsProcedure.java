package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

public class EnergyVialApplyEffectsProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (itemstack.getOrCreateTag().getDouble("energy") > 0) {
			if ((entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).effect_energy_cost > 0) {
				itemstack.getOrCreateTag().putDouble("energy",
						Math.max(itemstack.getOrCreateTag().getDouble("energy") - (entity.getCapability(BetterToolsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BetterToolsModVariables.PlayerVariables())).effect_energy_cost, 0));
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
					if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:glass_armor")))
							&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:glass_armor")))
							&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:glass_armor")))
							&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:glass_armor")))) {
						GlassArmorProcedureProcedure.execute(world, x, y, z, entity);
					}
				}
			}
		} else {
			itemstack.getOrCreateTag().putDouble("energy", 0);
		}
	}
}

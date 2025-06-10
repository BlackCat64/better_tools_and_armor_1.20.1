package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

public class CalculateEffectEnergyCostProcedure {
	public static double execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return 0;
		double cost = 0;
		double armor_pieces = 0;
		double i = 0;
		double piece_cost = 0;
		armor_pieces = EnergyVialActiveArmorPiecesProcedure.execute(entity, itemstack);
		i = 0;
		for (int index0 = 0; index0 < 4; index0++) {
			if (i == 0 && !itemstack.getOrCreateTag().getBoolean("boots_active") || i == 1 && !itemstack.getOrCreateTag().getBoolean("leggings_active") || i == 2 && !itemstack.getOrCreateTag().getBoolean("chestplate_active")
					|| i == 3 && !itemstack.getOrCreateTag().getBoolean("helmet_active")
					|| !((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) i)) : ItemStack.EMPTY)
							.is(ItemTags.create(new ResourceLocation("better_tools:effect_armor"))))) {
				i = i + 1;
				continue;
			}
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) i)) : ItemStack.EMPTY)
					.is(ItemTags.create(new ResourceLocation("better_tools:base_tier_effect_armor")))) {
				piece_cost = 150;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) i)) : ItemStack.EMPTY)
					.is(ItemTags.create(new ResourceLocation("better_tools:iron_tier_effect_armor")))) {
				piece_cost = 120;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) i)) : ItemStack.EMPTY)
					.is(ItemTags.create(new ResourceLocation("better_tools:diamond_tier_effect_armor")))) {
				piece_cost = 90;
			} else {
				piece_cost = 0;
			}
			if (armor_pieces == 2) {
				piece_cost = piece_cost * 0.6666666666666667;
			} else if (armor_pieces > 2) {
				piece_cost = piece_cost * 0.5;
			}
			cost = cost + piece_cost;
			i = i + 1;
		}
		return cost;
	}
}

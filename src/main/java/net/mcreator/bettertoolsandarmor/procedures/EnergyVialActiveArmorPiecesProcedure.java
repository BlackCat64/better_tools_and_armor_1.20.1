package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

public class EnergyVialActiveArmorPiecesProcedure {
	public static double execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return 0;
		double armor_pieces = 0;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:standard_effect_armor")))
				&& itemstack.getOrCreateTag().getBoolean("boots_active")) {
			armor_pieces = armor_pieces + 1;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:standard_effect_armor")))
				&& itemstack.getOrCreateTag().getBoolean("leggings_active")) {
			armor_pieces = armor_pieces + 1;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:standard_effect_armor")))
				&& itemstack.getOrCreateTag().getBoolean("chestplate_active")) {
			armor_pieces = armor_pieces + 1;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:standard_effect_armor")))
				&& itemstack.getOrCreateTag().getBoolean("helmet_active")) {
			armor_pieces = armor_pieces + 1;
		}
		if (IsWearingGlassArmorFullSetProcedure.execute(entity) && itemstack.getOrCreateTag().getBoolean("helmet_active") && itemstack.getOrCreateTag().getBoolean("chestplate_active") && itemstack.getOrCreateTag().getBoolean("leggings_active")
				&& itemstack.getOrCreateTag().getBoolean("boots_active")) {
			armor_pieces = 4;
		}
		return armor_pieces;
	}
}

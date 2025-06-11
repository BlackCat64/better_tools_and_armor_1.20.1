package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

public class GlassArmorEnergyCostProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		double i = 0;
		double cost = 0;
		for (int index0 = 0; index0 < 4; index0++) {
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) i)) : ItemStack.EMPTY)
					.is(ItemTags.create(new ResourceLocation("better_tools:base_glass_armor")))) {
				cost = cost + 50;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) i)) : ItemStack.EMPTY)
					.is(ItemTags.create(new ResourceLocation("better_tools:iron_glass_armor")))) {
				cost = cost + 40;
			} else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) i)) : ItemStack.EMPTY)
					.is(ItemTags.create(new ResourceLocation("better_tools:diamond_glass_armor")))) {
				cost = cost + 30;
			} else {
				cost = 0;
				break;
			}
			i = i + 1;
		}
		return cost;
	}
}


package net.mcreator.bettertoolsandarmor.enchantment;

import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.EquipmentSlot;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModEnchantments;

import java.util.List;

public class DesperationEnchantment extends Enchantment {
	public DesperationEnchantment(EquipmentSlot... slots) {
		super(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR_CHEST, slots);
	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {
		return this != ench && !List.of(BetterToolsModEnchantments.LIFE_AURA.get()).contains(ench);
	}
}

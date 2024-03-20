
package net.mcreator.bettertoolsandarmor.enchantment;

import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.EquipmentSlot;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModEnchantments;

import java.util.List;

public class LifeAuraEnchantment extends Enchantment {
	public LifeAuraEnchantment(EquipmentSlot... slots) {
		super(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR_CHEST, slots);
	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {
		return this != ench && !List.of(BetterToolsModEnchantments.DESPERATION.get()).contains(ench);
	}
}


package net.mcreator.bettertoolsandarmor.enchantment;

import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.List;

public class SmeltingTouchEnchantment extends Enchantment {
	public SmeltingTouchEnchantment(EquipmentSlot... slots) {
		super(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.DIGGER, slots);
	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {
		return this != ench && !List.of(Enchantments.SILK_TOUCH).contains(ench);
	}

	@Override
	public boolean isTreasureOnly() {
		return true;
	}
}


package net.mcreator.bettertoolsandarmor.enchantment;

import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EquipmentSlot;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModEnchantments;

import java.util.List;

public class ThunderShotEnchantment extends Enchantment {
	public ThunderShotEnchantment(EquipmentSlot... slots) {
		super(Enchantment.Rarity.RARE, EnchantmentCategory.BOW, slots);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {
		return this != ench && !List.of(BetterToolsModEnchantments.FREEZE_SHOT.get(), Enchantments.FLAMING_ARROWS).contains(ench);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack itemstack) {
		return Ingredient.of(new ItemStack(Items.BOW), new ItemStack(Items.CROSSBOW)).test(itemstack);
	}

	@Override
	public boolean isTreasureOnly() {
		return true;
	}
}

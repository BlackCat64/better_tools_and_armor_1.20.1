
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class TopazShovelItem extends ShovelItem {
	public TopazShovelItem() {
		super(new Tier() {
			public int getUses() {
				return 600;
			}

			public float getSpeed() {
				return 7f;
			}

			public float getAttackDamageBonus() {
				return 2.5f;
			}

			public int getLevel() {
				return 2;
			}

			public int getEnchantmentValue() {
				return 16;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.TOPAZ.get()));
			}
		}, 1, -2.8f, new Item.Properties());
	}
}

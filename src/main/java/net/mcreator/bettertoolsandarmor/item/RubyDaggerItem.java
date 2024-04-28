
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class RubyDaggerItem extends SwordItem {
	public RubyDaggerItem() {
		super(new Tier() {
			public int getUses() {
				return 300;
			}

			public float getSpeed() {
				return 12f;
			}

			public float getAttackDamageBonus() {
				return -2.5f;
			}

			public int getLevel() {
				return 1;
			}

			public int getEnchantmentValue() {
				return 12;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.RUBY.get()));
			}
		}, 3, -1f, new Item.Properties());
	}
}

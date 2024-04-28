
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class FlintDaggerItem extends SwordItem {
	public FlintDaggerItem() {
		super(new Tier() {
			public int getUses() {
				return 112;
			}

			public float getSpeed() {
				return 5f;
			}

			public float getAttackDamageBonus() {
				return -2.5f;
			}

			public int getLevel() {
				return 1;
			}

			public int getEnchantmentValue() {
				return 8;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(Items.FLINT));
			}
		}, 3, -1.2f, new Item.Properties());
	}
}

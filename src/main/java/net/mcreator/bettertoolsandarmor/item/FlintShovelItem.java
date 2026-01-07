
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.entity.ai.attributes.Attributes;

public class FlintShovelItem extends ShovelItem {
	public FlintShovelItem() {
		super(new Tier() {
			public int getUses() {
				return 150;
			}

			public float getSpeed() {
				return 5f;
			}

			public float getAttackDamageBonus() {
				return 2f;
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
		}, 1, -3f, new Item.Properties());
	}
}

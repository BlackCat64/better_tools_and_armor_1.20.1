
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.entity.ai.attributes.Attributes;

public class CrystallitePickaxeDiamondItem extends PickaxeItem {
	public CrystallitePickaxeDiamondItem() {
		super(new Tier() {
			public int getUses() {
				return 3600;
			}

			public float getSpeed() {
				return 14f;
			}

			public float getAttackDamageBonus() {
				return 5.5f;
			}

			public int getLevel() {
				return 4;
			}

			public int getEnchantmentValue() {
				return 20;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Items.DIAMOND));
			}
		}, 1, -2.8f, new Item.Properties().fireResistant());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A77Upgrade: \u00A7bDiamond"));
		list.add(Component.literal("\u00A77Ability:"));
		list.add(Component.literal("\u00A7bDiamond Hard - Increased Durability"));
	}
}

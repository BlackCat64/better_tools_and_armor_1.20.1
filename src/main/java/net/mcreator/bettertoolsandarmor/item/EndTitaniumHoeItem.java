
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.entity.ai.attributes.Attributes;

public class EndTitaniumHoeItem extends HoeItem {
	public EndTitaniumHoeItem() {
		super(new Tier() {
			public int getUses() {
				return 2700;
			}

			public float getSpeed() {
				return 16f;
			}

			public float getAttackDamageBonus() {
				return 2f;
			}

			public int getLevel() {
				return 4;
			}

			public int getEnchantmentValue() {
				return 18;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.END_TITANIUM_INGOT.get()));
			}
		}, 0, 0f, new Item.Properties().fireResistant());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A75Mines Purpur instantly"));
	}
}

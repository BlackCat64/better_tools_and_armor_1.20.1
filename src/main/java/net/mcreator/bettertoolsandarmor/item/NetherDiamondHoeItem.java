
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.entity.ai.attributes.Attributes;

public class NetherDiamondHoeItem extends HoeItem {
	public NetherDiamondHoeItem() {
		super(new Tier() {
			public int getUses() {
				return 1561;
			}

			public float getSpeed() {
				return 8f;
			}

			public float getAttackDamageBonus() {
				return 1f;
			}

			public int getLevel() {
				return 3;
			}

			public int getEnchantmentValue() {
				return 10;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.NETHER_DIAMOND.get()));
			}
		}, 0, 0f, new Item.Properties().fireResistant());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A74Plants may drop Charcoal"));
	}
}


package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.entity.ai.attributes.Attributes;

public class CrystalliteDaggerSculkItem extends SwordItem {
	public CrystalliteDaggerSculkItem() {
		super(new Tier() {
			public int getUses() {
				return 1687;
			}

			public float getSpeed() {
				return 14f;
			}

			public float getAttackDamageBonus() {
				return 3.5f;
			}

			public int getLevel() {
				return 4;
			}

			public int getEnchantmentValue() {
				return 20;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Items.ECHO_SHARD));
			}
		}, 3, -1.5f, new Item.Properties().fireResistant());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A77Upgrade: \u00A73Sculk"));
		list.add(Component.literal("\u00A77Ability:"));
		list.add(Component.literal("\u00A73Deep Dweller - Increased attack damage in the dark"));
	}
}

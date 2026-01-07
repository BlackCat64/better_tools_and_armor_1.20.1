
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.entity.ai.attributes.Attributes;

public class CrystalliteUpgradeSmithingTemplateItem extends Item {
	public CrystalliteUpgradeSmithingTemplateItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.COMMON));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A77Crystallite Upgrade"));
		list.add(Component.literal(""));
		list.add(Component.literal("\u00A77Applies to:"));
		list.add(Component.literal("\u00A79Crystallite Equipment"));
		list.add(Component.literal("\u00A77Ingredients:"));
		list.add(Component.literal("\u00A79Elemental Crystals"));
	}
}
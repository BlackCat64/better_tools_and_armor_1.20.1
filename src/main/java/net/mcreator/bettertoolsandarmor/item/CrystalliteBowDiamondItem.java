
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BowItem;
import net.minecraft.network.chat.Component;

import java.util.List;

public class CrystalliteBowDiamondItem extends BowItem {
	public CrystalliteBowDiamondItem() {
		super(new Item.Properties().durability(3600).fireResistant().rarity(Rarity.COMMON));
	}

	@Override
	public int getEnchantmentValue() {
		return 20;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A77Upgrade: \u00A7bDiamond"));
		list.add(Component.literal("\u00A77Abilities:"));
		list.add(Component.literal("\u00A7bCritical - Arrows sometimes deal very high damage"));
		list.add(Component.literal("\u00A7bDiamond Hard - Increased Durability"));
	}
}

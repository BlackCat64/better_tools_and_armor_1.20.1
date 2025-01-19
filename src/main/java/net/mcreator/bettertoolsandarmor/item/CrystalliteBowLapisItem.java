
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BowItem;
import net.minecraft.network.chat.Component;

import java.util.List;

public class CrystalliteBowLapisItem extends BowItem {
	public CrystalliteBowLapisItem() {
		super(new Item.Properties().durability(1800).fireResistant().rarity(Rarity.COMMON));
	}

	@Override
	public int getEnchantmentValue() {
		return 30;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A77Upgrade: \u00A79Lapis Lazuli"));
		list.add(Component.literal("\u00A77Ability:"));
		list.add(Component.literal("\u00A79Magic - XP drops increase with distance from the target"));
	}
}

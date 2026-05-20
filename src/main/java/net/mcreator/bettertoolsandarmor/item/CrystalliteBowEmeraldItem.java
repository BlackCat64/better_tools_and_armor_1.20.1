
package net.mcreator.bettertoolsandarmor.item;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;

import java.util.List;

public class CrystalliteBowEmeraldItem extends Item {
	public CrystalliteBowEmeraldItem() {
		super(new Item.Properties().durability(1800).fireResistant().rarity(Rarity.COMMON));
	}

	@Override
	public int getEnchantmentValue() {
		return 20;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.better_tools.crystallite_bow_emerald.description_0"));
	}
}

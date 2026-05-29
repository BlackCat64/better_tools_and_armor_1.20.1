package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class CrystalliteShardsItem extends Item {
	public CrystalliteShardsItem() {
		super(new Item.Properties().fireResistant());
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.EAT;
	}
}